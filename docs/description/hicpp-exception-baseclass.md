clang-tidy - hicpp-exception-baseclass

</div>

# hicpp-exception-baseclass

Ensure that every value that in a `throw` expression is an instance of
`std::exception`.

This enforces [rule
15.1](http://www.codingstandard.com/section/15-1-throwing-an-exception/)
of the High Integrity C++ Coding Standard.

``` c++
class custom_exception {};

void throwing() noexcept(false) {
  // Problematic throw expressions.
  throw int(42);
  throw custom_exception();
}

class mathematical_error : public std::exception {};

void throwing2() noexcept(false) {
  // These kind of throws are ok.
  throw mathematical_error();
  throw std::runtime_error();
  throw std::exception();
}
```
