modernize-loop-convert
======================

This check converts `for(...; ...; ...)` loops to use the new
range-based loops in C++11.

Three kinds of loops can be converted:

-   Loops over statically allocated arrays.
-   Loops over containers, using iterators.
-   Loops over array-like containers, using `operator[]` and `at()`.

MinConfidence option
--------------------

risky ^^^^^

In loops where the container expression is more complex than just a
reference to a declared expression (a variable, function, enum, etc.),
and some part of it appears elsewhere in the loop, we lower our
confidence in the transformation due to the increased risk of changing
semantics. Transformations for these loops are marked as `risky`, and
thus will only be converted if the minimum required confidence level is
set to `risky`.

.. code-block:: c++

int arr\[10\]\[20\]; int l = 5;

for (int j = 0; j &lt; 20; ++j) int k = arr\[l\]\[j\] + l; // using l
outside arr\[l\] is considered risky

for (int i = 0; i &lt; obj.getVector().size(); ++i) obj.foo(10); //
using ‘obj’ is considered risky

See
:ref:`Range-based loops evaluate end() only once<IncorrectRiskyTransformation>`
for an example of an incorrect transformation when the minimum required
confidence level is set to `risky`.

reasonable (Default) ^^^^^^^^^^^^^^^^^^^^

If a loop calls `.end()` or `.size()` after each iteration, the
transformation for that loop is marked as `reasonable`, and thus will be
converted if the required confidence level is set to `reasonable`
(default) or lower.

.. code-block:: c++

// using size() is considered reasonable for (int i = 0; i &lt;
container.size(); ++i) cout &lt;&lt; container\[i\];

safe ^^^^

Any other loops that do not match the above criteria to be marked as
`risky` or `reasonable` are marked `safe`, and thus will be converted if
the required confidence level is set to `safe` or lower.

.. code-block:: c++

int arr\[\] = {1,2,3};

for (int i = 0; i &lt; 3; ++i) cout &lt;&lt; arr\[i\];

Example
-------

Original:

.. code-block:: c++

const int N = 5; int arr\[\] = {1,2,3,4,5}; vector<int> v;
v.push\_back(1); v.push\_back(2); v.push\_back(3);

// safe conversion for (int i = 0; i &lt; N; ++i) cout &lt;&lt;
arr\[i\];

// reasonable conversion for (vector<int>::iterator it = v.begin(); it
!= v.end(); ++it) cout &lt;&lt; \*it;

// reasonable conversion for (int i = 0; i &lt; v.size(); ++i) cout
&lt;&lt; v\[i\];

After applying the check with minimum confidence level set to
`reasonable` (default):

.. code-block:: c++

const int N = 5; int arr\[\] = {1,2,3,4,5}; vector<int> v;
v.push\_back(1); v.push\_back(2); v.push\_back(3);

// safe conversion for (auto & elem : arr) cout &lt;&lt; elem;

// reasonable conversion for (auto & elem : v) cout &lt;&lt; elem;

// reasonable conversion for (auto & elem : v) cout &lt;&lt; elem;

Limitations
-----------

There are certain situations where the tool may erroneously perform
transformations that remove information and change semantics. Users of
the tool should be aware of the behaviour and limitations of the check
outlined by the cases below.

Comments inside loop headers ^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Comments inside the original loop header are ignored and deleted when
transformed.

.. code-block:: c++

for (int i = 0; i &lt; N; /\* This will be deleted \*/ ++i) { }

Range-based loops evaluate end() only once
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The C++11 range-based for loop calls `.end()` only once during the
initialization of the loop. If in the original loop `.end()` is called
after each iteration the semantics of the transformed loop may differ.

.. code-block:: c++

// The following is semantically equivalent to the C++11 range-based for
loop, // therefore the semantics of the header will not change. for
(iterator it = container.begin(), e = container.end(); it != e; ++it) {
}

// Instead of calling .end() after each iteration, this loop will be //
transformed to call .end() only once during the initialization of the
loop, // which may affect semantics. for (iterator it =
container.begin(); it != container.end(); ++it) { }

.. \_IncorrectRiskyTransformation:

As explained above, calling member functions of the container in the
body of the loop is considered `risky`. If the called member function
modifies the container the semantics of the converted loop will differ
due to `.end()` being called only once.

.. code-block:: c++

bool flag = false; for (vector<T>::iterator it = vec.begin(); it !=
vec.end(); ++it) { // Add a copy of the first element to the end of the
vector. if (!flag) { // This line makes this transformation ‘risky’.
vec.push\_back(*it); flag = true; } cout &lt;&lt; *it; }

The original code above prints out the contents of the container
including the newly added element while the converted loop, shown below,
will only print the original contents and not the newly added element.

.. code-block:: c++

bool flag = false; for (auto & elem : vec) { // Add a copy of the first
element to the end of the vector. if (!flag) { // This line makes this
transformation ‘risky’ vec.push\_back(elem); flag = true; } cout
&lt;&lt; elem; }

Semantics will also be affected if `.end()` has side effects. For
example, in the case where calls to `.end()` are logged the semantics
will change in the transformed loop if `.end()` was originally called
after each iteration.

.. code-block:: c++

iterator end() { num\_of\_end\_calls++; return container.end(); }

Overloaded operator-&gt;() with side effects
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Similarly, if `operator->()` was overloaded to have side effects, such
as logging, the semantics will change. If the iterator’s `operator->()`
was used in the original loop it will be replaced with
`<container element>.<member>` instead due to the implicit dereference
as part of the range-based for loop. Therefore any side effect of the
overloaded `operator->()` will no longer be performed.

.. code-block:: c++

for (iterator it = c.begin(); it != c.end(); ++it) { it-&gt;func(); //
Using operator-&gt;() } // Will be transformed to: for (auto & elem : c)
{ elem.func(); // No longer using operator-&gt;() }

Pointers and references to containers
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

While most of the check’s risk analysis is dedicated to determining
whether the iterator or container was modified within the loop, it is
possible to circumvent the analysis by accessing and modifying the
container through a pointer or reference.

If the container were directly used instead of using the pointer or
reference the following transformation would have only been applied at
the `risky` level since calling a member function of the container is
considered `risky`. The check cannot identify expressions associated
with the container that are different than the one used in the loop
header, therefore the transformation below ends up being performed at
the `safe` level.

.. code-block:: c++

vector<int> vec;

vector<int> \*ptr = &vec; vector<int> &ref = vec;

for (vector<int>::iterator it = vec.begin(), e = vec.end(); it != e;
++it) { if (!flag) { // Accessing and modifying the container is
considered risky, but the risk // level is not raised here.
ptr-&gt;push\_back(*it); ref.push\_back(*it); flag = true; } }

OpenMP ^^^^^^

As range-based for loops are only available since OpenMP 5, this check
should not been used on code with a compatibility requirements of OpenMP
prior to version 5. It is **intentional** that this check does not make
any attempts to exclude incorrect diagnostics on OpenMP for loops prior
to OpenMP 5.

To prevent this check to be applied (and to break) OpenMP for loops but
still be applied to non-OpenMP for loops the usage of `NOLINT` (see
:ref:`clang-tidy-nolint`) on the specific for loops is recommended.