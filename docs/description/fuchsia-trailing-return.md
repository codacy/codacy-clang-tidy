fuchsia-trailing-return
=======================

Functions that have trailing returns are disallowed, except for those
using `decltype` specifiers and lambda with otherwise unutterable return
types.

For example:

.. code-block:: c++

// No warning int add\_one(const int arg) { return arg; }

// Warning auto get\_add\_one() -&gt; int (\*)(const int) { return
add\_one; }

Exceptions are made for lambdas and `decltype` specifiers:

.. code-block:: c++

// No warning auto lambda = [](double%20x,%20double%20y) -&gt; double
{return x + y;};

// No warning template &lt;typename T1, typename T2&gt; auto fn(const T1
&lhs, const T2 &rhs) -&gt; decltype(lhs + rhs) { return lhs + rhs; }

See the features disallowed in Fuchsia at
https://fuchsia.googlesource.com/zircon/+/master/docs/cxx.md
