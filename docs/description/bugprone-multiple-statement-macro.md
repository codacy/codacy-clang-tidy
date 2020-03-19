bugprone-multiple-statement-macro
=================================

Detect multiple statement macros that are used in unbraced conditionals.
Only the first statement of the macro will be inside the conditional and
the other ones will be executed unconditionally.

Example:

.. code-block:: c++

\#define INCREMENT\_TWO(x, y) (x)++; (y)++ if (do\_increment)
INCREMENT\_TWO(a, b); // (b)++ will be executed unconditionally.
