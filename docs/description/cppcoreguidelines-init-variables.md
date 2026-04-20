clang-tidy - cppcoreguidelines-init-variables

</div>

# cppcoreguidelines-init-variables

Checks whether there are local variables that are declared without an
initial value. These may lead to unexpected behavior if there is a code
path that reads the variable before assigning to it.

This rule is part of the [Type safety
(Type.5)](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#Pro-type-init)
profile and
[ES.20](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#Res-always)
from the C++ Core Guidelines.

Only integers, booleans, floats, doubles and pointers are checked. The
fix option initializes all detected values with the value of zero. An
exception is float and double types, which are initialized to NaN.

As an example a function that looks like this:

``` c++
void function() {
  int x;
  char *txt;
  double d;

  // Rest of the function.
}
```

Would be rewritten to look like this:

``` c++
#include <math.h>

void function() {
  int x = 0;
  char *txt = nullptr;
  double d = NAN;

  // Rest of the function.
}
```

It warns for the uninitialized enum case, but without a FixIt:

``` c++
enum A {A1, A2, A3};
enum A_c : char { A_c1, A_c2, A_c3 };
enum class B { B1, B2, B3 };
enum class B_i : int { B_i1, B_i2, B_i3 };
void function() {
  A a;     // Warning: variable 'a' is not initialized
  A_c a_c; // Warning: variable 'a_c' is not initialized
  B b;     // Warning: variable 'b' is not initialized
  B_i b_i; // Warning: variable 'b_i' is not initialized
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

MathHeader

A string specifying the header to include to get the definition of
<span class="title-ref">NAN</span>. Default is
<span class="title-ref">\<math.h\></span>.

</div>
