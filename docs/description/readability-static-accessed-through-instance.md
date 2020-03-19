readability-static-accessed-through-instance
============================================

Checks for member expressions that access static members through
instances, and replaces them with uses of the appropriate qualified-id.

Example:

The following code:

.. code-block:: c++

struct C { static void foo(); static int x; };

C \*c1 = new C(); c1-&gt;foo(); c1-&gt;x;

is changed to:

.. code-block:: c++

C \*c1 = new C(); C::foo(); C::x;
