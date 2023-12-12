clang-tidy - performance-trivially-destructible

</div>

# performance-trivially-destructible

Finds types that could be made trivially-destructible by removing
out-of-line defaulted destructor declarations.

``` c++
struct A: TrivialType {
  ~A(); // Makes A non-trivially-destructible.
  TrivialType trivial_fields;
};
A::~A() = default;
```
