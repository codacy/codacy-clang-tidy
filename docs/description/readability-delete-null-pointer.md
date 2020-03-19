readability-delete-null-pointer
===============================

Checks the `if` statements where a pointer’s existence is checked and
then deletes the pointer. The check is unnecessary as deleting a null
pointer has no effect.

.. code:: c++

int \*p; if (p) delete p;
