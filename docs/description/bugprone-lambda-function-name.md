bugprone-lambda-function-name
=============================

Checks for attempts to get the name of a function from within a lambda
expression. The name of a lambda is always something like `operator()`,
which is almost never what was intended.

Example:

.. code-block:: c++

void FancyFunction() { \[\] { printf("Called from %s`\n`{=tex}",
**func**); }(); \[\] { printf("Now called from %s`\n`{=tex}",
**FUNCTION**); }(); }

Output::

Called from operator() Now called from operator()

Likely intended output::

Called from FancyFunction Now called from FancyFunction
