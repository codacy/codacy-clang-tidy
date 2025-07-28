clang-tidy - modernize-use-integer-sign-comparison

</div>

# modernize-use-integer-sign-comparison

Replace comparisons between signed and unsigned integers with their safe
C++20 `std::cmp_*` alternative, if available.

The check provides a replacement only for C++20 or later, otherwise it
highlights the problem and expects the user to fix it manually.

Examples of fixes created by the check:

``` c++
unsigned int func(int a, unsigned int b) {
  return a == b;
}
```

becomes

``` c++
#include <utility>

unsigned int func(int a, unsigned int b) {
  return std::cmp_equal(a, b);
}
```

## Options

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>

<div class="option">

EnableQtSupport

Makes C++17 `q20::cmp_*` alternative available for Qt-based
applications. Default is <span class="title-ref">false</span>.

</div>
