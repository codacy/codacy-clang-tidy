modernize-use-nullptr
=====================

The check converts the usage of null pointer constants (eg. `NULL`, `0`)
to use the new C++11 `nullptr` keyword.

Example
-------

.. code-block:: c++

void assignment() { char *a = NULL; char *b = 0; char c = 0; }

int \*ret\_ptr() { return 0; }

transforms to:

.. code-block:: c++

void assignment() { char *a = nullptr; char *b = nullptr; char c = 0; }

int \*ret\_ptr() { return nullptr; }

Options
-------

.. option:: NullMacros

Comma-separated list of macro names that will be transformed along with
`NULL`. By default this check will only replace the `NULL` macro and
will skip any similar user-defined macros.

Example \^\^\^\^\^\^\^

.. code-block:: c++

\#define MY\_NULL (void*)0 void assignment() { void *p = MY\_NULL; }

transforms to:

.. code-block:: c++

\#define MY\_NULL NULL void assignment() { int \*p = nullptr; }

if the :option:`NullMacros` option is set to `MY_NULL`.
