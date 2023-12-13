clang-tidy - performance-type-promotion-in-math-fn

</div>

# performance-type-promotion-in-math-fn

Finds calls to C math library functions (from `math.h` or, in C++,
`cmath`) with implicit `float` to `double` promotions.

For example, warns on `::sin(0.f)`, because this funciton's parameter is
a double. You probably meant to call `std::sin(0.f)` (in C++), or
`sinf(0.f)` (in C).

``` c++
float a;
asin(a);

// becomes

float a;
std::asin(a);
```
