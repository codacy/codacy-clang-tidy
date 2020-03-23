hicpp-exception-baseclass
=========================

Ensure that every value that in a `throw` expression is an instance of
`std::exception`.

This enforces
[rule 15.1](http://www.codingstandard.com/section/15-1-throwing-an-exception/)
of the High Integrity C++ Coding Standard.

.. code-block:: c++

class custom\_exception {};

void throwing() noexcept(false) { // Problematic throw expressions.
throw int(42); throw custom\_exception(); }

class mathematical\_error : public std::exception {};

void throwing2() noexcept(false) { // These kind of throws are ok. throw
mathematical\_error(); throw std::runtime\_error(); throw
std::exception(); }
