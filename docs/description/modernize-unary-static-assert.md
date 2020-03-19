modernize-unary-static-assert
=============================

The check diagnoses any `static_assert` declaration with an empty string
literal and provides a fix-it to replace the declaration with a
single-argument `static_assert` declaration.

The check is only applicable for C++17 and later code.

The following code:

.. code-block:: c++

void f\_textless(int a) { static\_assert(sizeof(a) &lt;= 10, ""); }

is replaced by:

.. code-block:: c++

void f\_textless(int a) { static\_assert(sizeof(a) &lt;= 10); }
