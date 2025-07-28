clang-tidy - misc-include-cleaner

</div>

# misc-include-cleaner

Checks for unused and missing includes. Generates findings only for the
main file of a translation unit. Findings correspond to
<https://clangd.llvm.org/design/include-cleaner>.

Example:

``` c++
// foo.h
class Foo{};
// bar.h
#include "baz.h"
class Bar{};
// baz.h
class Baz{};
// main.cc
#include "bar.h" // OK: uses class Bar from bar.h
#include "foo.h" // warning: unused include "foo.h"
Bar bar;
Baz baz; // warning: missing include "baz.h"
```

## Options

<div class="option">

IgnoreHeaders

A semicolon-separated list of regexes to disable insertion/removal of
header files that match this regex as a suffix. E.g.,
<span class="title-ref">foo/.\*</span> disables insertion/removal for
all headers under the directory <span class="title-ref">foo</span>. By
default, no headers will be ignored.

</div>

<div class="option">

DeduplicateFindings

A boolean that controls whether the check should deduplicate findings
for the same symbol. Defaults to <span class="title-ref">true</span>.

</div>
