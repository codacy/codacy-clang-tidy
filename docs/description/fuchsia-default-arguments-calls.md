fuchsia-default-arguments-calls
===============================

Warns if a function or method is called with default arguments.

For example, given the declaration:

    int foo(int value = 5) { return value; }

A function call expression that uses a default argument will be
diagnosed. Calling it without defaults will not cause a warning:

    foo();  // warning
    foo(0); // no warning

See the features disallowed in Fuchsia at
<https://fuchsia.googlesource.com/zircon/+/master/docs/cxx.md>
