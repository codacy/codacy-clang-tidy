fuchsia-overloaded-operator
===========================

Warns if an operator is overloaded, except for the assignment (copy and
move) operators.

For example:

    int operator+(int);     // Warning

    B &operator=(const B &Other);  // No warning
    B &operator=(B &&Other) // No warning

See the features disallowed in Fuchsia at
<https://fuchsia.googlesource.com/zircon/+/master/docs/cxx.md>
