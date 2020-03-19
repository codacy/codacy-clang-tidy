performance-implicit-conversion-in-loop
=======================================

This warning appears in a range-based loop with a loop variable of const
ref type where the type of the variable does not match the one returned
by the iterator. This means that an implicit conversion happens, which
can for example result in expensive deep copies.

Example:

.. code-block:: c++

map&lt;int, vector<string>&gt; my\_map; for (const pair&lt;int,
vector<string>&gt;& p : my\_map) {} // The iterator type is in fact
pair&lt;const int, vector<string>&gt;, which means // that the compiler
added a conversion, resulting in a copy of the vectors.

The easiest solution is usually to use `const auto&` instead of writing
the type manually.
