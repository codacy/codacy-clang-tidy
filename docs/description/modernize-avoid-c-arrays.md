clang-tidy - modernize-avoid-c-arrays

</div>

# modernize-avoid-c-arrays

<span class="title-ref">cppcoreguidelines-avoid-c-arrays</span>
redirects here as an alias for this check.

<span class="title-ref">hicpp-avoid-c-arrays</span> redirects here as an
alias for this check.

Finds C-style array types and recommend to use `std::array<>` /
`std::vector<>`. All types of C arrays are diagnosed.

For parameters of incomplete C-style array type, it would be better to
use `std::span` / `gsl::span` as replacement.

However, fix-it are potentially dangerous in header files and are
therefore not emitted right now.

``` c++
int a[] = {1, 2}; // warning: do not declare C-style arrays, use 'std::array' instead

int b[1]; // warning: do not declare C-style arrays, use 'std::array' instead

void foo() {
  int c[b[0]]; // warning: do not declare C VLA arrays, use 'std::vector' instead
}

template <typename T, int Size>
class array {
  T d[Size]; // warning: do not declare C-style arrays, use 'std::array' instead

  int e[1]; // warning: do not declare C-style arrays, use 'std::array' instead
};

array<int[4], 2> d; // warning: do not declare C-style arrays, use 'std::array' instead

using k = int[4]; // warning: do not declare C-style arrays, use 'std::array' instead
```

However, the `extern "C"` code is ignored, since it is common to share
such headers between C code, and C++ code.

``` c++
// Some header
extern "C" {

int f[] = {1, 2}; // not diagnosed

int j[1]; // not diagnosed

inline void bar() {
  {
    int j[j[0]]; // not diagnosed
  }
}

}
```

Similarly, the `main()` function is ignored. Its second and third
parameters can be either `char* argv[]` or `char** argv`, but cannot be
`std::array<>`.

<div class="option">

AllowStringArrays

When set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), variables of character array type
with deduced length, initialized directly from string literals, will be
ignored. This option doesn't affect cases where length can't be deduced,
resembling pointers, as seen in class members and parameters. Example:

``` c++
const char name[] = "Some name";
```

</div>
