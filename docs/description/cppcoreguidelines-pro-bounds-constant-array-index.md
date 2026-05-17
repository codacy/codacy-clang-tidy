clang-tidy - cppcoreguidelines-pro-bounds-constant-array-index

</div>

# cppcoreguidelines-pro-bounds-constant-array-index

This check flags all array subscript expressions on static arrays and
`std::arrays` that either do not have a constant integer expression
index or are out of bounds (for `std::array`). For out-of-bounds
checking of static arrays, see the
<span class="title-ref">-Warray-bounds</span> Clang diagnostic.

This rule is part of the "Bounds safety" profile of the C++ Core
Guidelines, see
<https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#Pro-bounds-arrayindex>.

## Options

<div class="option">

GslHeader

The check can generate fixes after this option has been set to the name
of the include file that contains `gsl::at()`, e.g.
<span class="title-ref">"gsl/gsl.h"</span>.

</div>

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>
