# cppcoreguidelines-init-variables

Checks whether there are local variables that are declared without an
initial value. These may lead to unexpected behaviour if there is a code
path that reads the variable before assigning to it.

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
<span class="title-ref">math.h</span>.

</div>
