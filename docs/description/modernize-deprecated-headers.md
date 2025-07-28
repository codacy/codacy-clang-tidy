clang-tidy - modernize-deprecated-headers

</div>

# modernize-deprecated-headers

Some headers from C library were deprecated in C++ and are no longer
welcome in C++ codebases. Some have no effect in C++. For more details
refer to the C++14 Standard \[depr.c.headers\] section.

This check replaces C standard library headers with their C++
alternatives and removes redundant ones.

``` c++
// C++ source file...
#include <assert.h>
#include <stdbool.h>

// becomes

#include <cassert>
// No 'stdbool.h' here.
```

Important note: the Standard doesn't guarantee that the C++ headers
declare all the same functions in the global namespace. The check in its
current form can break the code that uses library symbols from the
global namespace.

- <span class="title-ref">\<assert.h\></span>
- <span class="title-ref">\<complex.h\></span>
- <span class="title-ref">\<ctype.h\></span>
- <span class="title-ref">\<errno.h\></span>
- <span class="title-ref">\<fenv.h\></span> // deprecated since C++11
- <span class="title-ref">\<float.h\></span>
- <span class="title-ref">\<inttypes.h\></span>
- <span class="title-ref">\<limits.h\></span>
- <span class="title-ref">\<locale.h\></span>
- <span class="title-ref">\<math.h\></span>
- <span class="title-ref">\<setjmp.h\></span>
- <span class="title-ref">\<signal.h\></span>
- <span class="title-ref">\<stdarg.h\></span>
- <span class="title-ref">\<stddef.h\></span>
- <span class="title-ref">\<stdint.h\></span>
- <span class="title-ref">\<stdio.h\></span>
- <span class="title-ref">\<stdlib.h\></span>
- <span class="title-ref">\<string.h\></span>
- <span class="title-ref">\<tgmath.h\></span> // deprecated since C++11
- <span class="title-ref">\<time.h\></span>
- <span class="title-ref">\<uchar.h\></span> // deprecated since C++11
- <span class="title-ref">\<wchar.h\></span>
- <span class="title-ref">\<wctype.h\></span>

If the specified standard is older than C++11 the check will only
replace headers deprecated before C++11, otherwise -- every header that
appeared in the previous list.

These headers don't have effect in C++:

- <span class="title-ref">\<iso646.h\></span>
- <span class="title-ref">\<stdalign.h\></span>
- <span class="title-ref">\<stdbool.h\></span>

The checker ignores <span class="title-ref">include</span> directives
within <span class="title-ref">extern "C" { ... }</span> blocks, since a
library might want to expose some API for C and C++ libraries.

``` c++
// C++ source file...
extern "C" {
#include <assert.h>  // Left intact.
#include <stdbool.h> // Left intact.
}
```

## Options

<div class="option">

CheckHeaderFile

<span class="title-ref">clang-tidy</span> cannot know if the header file
included by the currently analyzed C++ source file is not included by
any other C source files. Hence, to omit false-positives and wrong
fixit-hints, we ignore emitting reports into header files. One can set
this option to <span class="title-ref">true</span> if they know that the
header files in the project are only used by C++ source file. Default is
<span class="title-ref">false</span>.

</div>
