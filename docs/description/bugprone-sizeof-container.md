bugprone-sizeof-container
=========================

The check finds usages of `sizeof` on expressions of STL container
types. Most likely the user wanted to use `.size()` instead.

All class/struct types declared in namespace `std::` having a const
`size()` method are considered containers, with the exception of
`std::bitset` and `std::array`.

Examples:

.. code-block:: c++

std::string s; int a = 47 + sizeof(s); // warning: sizeof() doesn't
return the size of the container. Did you mean .size()?

int b = sizeof(std::string); // no warning, probably intended.

std::string array\_of\_strings\[10\]; int c = sizeof(array\_of\_strings)
/ sizeof(array\_of\_strings\[0\]); // no warning, definitely intended.

std::array\<int, 3\> std\_array; int d = sizeof(std\_array); // no
warning, probably intended.
