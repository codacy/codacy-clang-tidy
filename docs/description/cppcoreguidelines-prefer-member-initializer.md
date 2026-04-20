clang-tidy - cppcoreguidelines-prefer-member-initializer

</div>

# cppcoreguidelines-prefer-member-initializer

Finds member initializations in the constructor body which can be
converted into member initializers of the constructor instead. This not
only improves the readability of the code but also positively affects
its performance. Class-member assignments inside a control statement or
following the first control statement are ignored.

This check implements
[C.49](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#c49-prefer-initialization-to-assignment-in-constructors)
from the C++ Core Guidelines.

Please note, that this check does not enforce rule
[C.48](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#c48-prefer-in-class-initializers-to-member-initializers-in-constructors-for-constant-initializers)
from the C++ Core Guidelines. For that purpose see check
`modernize-use-default-member-init <../modernize/use-default-member-init>`.

## Example 1

``` c++
class C {
  int n;
  int m;
public:
  C() {
    n = 1; // Literal in default constructor
    if (dice())
      return;
    m = 1;
  }
};
```

Here `n` can be initialized in the constructor initializer list, unlike
`m`, as `m`'s initialization follows a control statement (`if`):

``` c++
class C {
  int n;
  int m;
public:
  C(): n(1) {
    if (dice())
      return;
    m = 1;
  }
};
```

## Example 2

``` c++
class C {
  int n;
  int m;
public:
  C(int nn, int mm) {
    n = nn; // Neither default constructor nor literal
    if (dice())
      return;
    m = mm;
  }
};
```

Here `n` can be initialized in the constructor initializer list, unlike
`m`, as `m`'s initialization follows a control statement (`if`):

``` c++
C(int nn, int mm) : n(nn) {
  if (dice())
    return;
  m = mm;
}
```
