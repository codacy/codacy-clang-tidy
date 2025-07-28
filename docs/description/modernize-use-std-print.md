clang-tidy - modernize-use-std-print

</div>

# modernize-use-std-print

Converts calls to `printf`, `fprintf`, `absl::PrintF` and
`absl::FPrintf` to equivalent calls to C++23's `std::print` or
`std::println` as appropriate, modifying the format string
appropriately. The replaced and replacement functions can be customised
by configuration options. Each argument that is the result of a call to
`std::string::c_str()` and `std::string::data()` will have that
now-unnecessary call removed in a similar manner to the
<span class="title-ref">readability-redundant-string-cstr</span> check.

In other words, it turns lines like:

``` c++
fprintf(stderr, "The %s is %3d\n", description.c_str(), value);
```

into:

``` c++
std::println(stderr, "The {} is {:3}", description, value);
```

If the <span class="title-ref">ReplacementPrintFunction</span> or
<span class="title-ref">ReplacementPrintlnFunction</span> options are
left at or set to their default values then this check is only enabled
with <span class="title-ref">-std=c++23</span> or later.

Macros starting with `PRI` and `__PRI` from
<span class="title-ref">\<inttypes.h\></span> are expanded, escaping is
handled and adjacent strings are concatenated to form a single
`StringLiteral` before the format string is converted. Use of any other
macros in the format string will cause a warning message to be emitted
and no conversion will be performed. The converted format string will
always be a single string literal.

The check doesn't do a bad job, but it's not perfect. In particular:

- It assumes that the format string is correct for the arguments. If you
  get any warnings when compiling with
  <span class="title-ref">-Wformat</span> then misbehaviour is possible.
- At the point that the check runs, the AST contains a single
  `StringLiteral` for the format string where escapes have been
  expanded. The check tries to reconstruct escape sequences, they may
  not be the same as they were written (e.g. `"\x41\x0a"` will become
  `"A\n"` and `"ab" "cd"` will become `"abcd"`.)
- It supports field widths, precision, positional arguments, leading
  zeros, leading `+`, alignment and alternative forms.
- Use of any unsupported flags or specifiers will cause the entire
  statement to be left alone and a warning to be emitted. Particular
  unsupported features are:
  - The `%'` flag for thousands separators.
  - The glibc extension `%m`.
- `printf` and similar functions return the number of characters
  printed. `std::print` does not. This means that any invocations that
  use the return value will not be converted. Unfortunately this
  currently includes explicitly-casting to `void`. Deficiencies in this
  check mean that any invocations inside `GCC` compound statements
  cannot be converted even if the resulting value is not used.

If conversion would be incomplete or unsafe then the entire invocation
will be left unchanged.

If the call is deemed suitable for conversion then:

- `printf`, `fprintf`, `absl::PrintF`, `absl::FPrintF` and any functions
  specified by the <span class="title-ref">PrintfLikeFunctions</span>
  option or <span class="title-ref">FprintfLikeFunctions</span> are
  replaced with the function specified by the
  <span class="title-ref">ReplacementPrintlnFunction</span> option if
  the format string ends with `\n` or
  <span class="title-ref">ReplacementPrintFunction</span> otherwise.
- the format string is rewritten to use the `std::formatter` language.
  If a `\n` is found at the end of the format string not preceded by `r`
  then it is removed and
  <span class="title-ref">ReplacementPrintlnFunction</span> is used
  rather than <span class="title-ref">ReplacementPrintFunction</span>.
- any arguments that corresponded to `%p` specifiers that
  `std::formatter` wouldn't accept are wrapped in a `static_cast` to
  `const void *`.
- any arguments that corresponded to `%s` specifiers where the argument
  is of `signed char` or `unsigned char` type are wrapped in a
  `reinterpret_cast<const char *>`.
- any arguments where the format string and the parameter differ in
  signedness will be wrapped in an appropriate `static_cast` if
  <span class="title-ref">StrictMode</span> is enabled.
- any arguments that end in a call to `std::string::c_str()` or
  `std::string::data()` will have that call removed.

## Options

<div class="option">

StrictMode

When <span class="title-ref">true</span>, the check will add casts when
converting from variadic functions like `printf` and printing signed or
unsigned integer types (including fixed-width integer types from
`<cstdint>`, `ptrdiff_t`, `size_t` and `ssize_t`) as the opposite
signedness to ensure that the output matches that of `printf`. This does
not apply when converting from non-variadic functions such as
`absl::PrintF` and `fmt::printf`. For example, with
<span class="title-ref">StrictMode</span> enabled:

``` c++
int i = -42;
unsigned int u = 0xffffffff;
printf("%u %d\n", i, u);
```

would be converted to:

``` c++
std::print("{} {}\n", static_cast<unsigned int>(i), static_cast<int>(u));
```

to ensure that the output will continue to be the unsigned
representation of <span class="title-ref">-42</span> and the signed
representation of <span class="title-ref">0xffffffff</span> (often
<span class="title-ref">4294967254</span> and
<span class="title-ref">-1</span> respectively.) When
<span class="title-ref">false</span> (which is the default), these casts
will not be added which may cause a change in the output.

</div>

<div class="option">

PrintfLikeFunctions

A semicolon-separated list of (fully qualified) function names to
replace, with the requirement that the first parameter contains the
printf-style format string and the arguments to be formatted follow
immediately afterwards. Qualified member function names are supported,
but the replacement function name must be unqualified. If neither this
option nor <span class="title-ref">FprintfLikeFunctions</span> are set
then the default value for this option is
<span class="title-ref">printf; absl::PrintF</span>, otherwise it is
empty.

</div>

<div class="option">

FprintfLikeFunctions

A semicolon-separated list of (fully qualified) function names to
replace, with the requirement that the first parameter is retained, the
second parameter contains the printf-style format string and the
arguments to be formatted follow immediately afterwards. Qualified
member function names are supported, but the replacement function name
must be unqualified. If neither this option nor
<span class="title-ref">PrintfLikeFunctions</span> are set then the
default value for this option is <span class="title-ref">fprintf;
absl::FPrintF</span>, otherwise it is empty.

</div>

<div class="option">

ReplacementPrintFunction

The function that will be used to replace `printf`, `fprintf` etc.
during conversion rather than the default `std::print` when the
originalformat string does not end with `\n`. It is expected that the
function provides an interface that is compatible with `std::print`. A
suitable candidate would be `fmt::print`.

</div>

<div class="option">

ReplacementPrintlnFunction

The function that will be used to replace `printf`, `fprintf` etc.
during conversion rather than the default `std::println` when the
original format string ends with `\n`. It is expected that the function
provides an interface that is compatible with `std::println`. A suitable
candidate would be `fmt::println`.

</div>

<div class="option">

PrintHeader

The header that must be included for the declaration of
<span class="title-ref">ReplacementPrintFunction</span> so that a
`#include` directive can be added if required. If
<span class="title-ref">ReplacementPrintFunction</span> is `std::print`
then this option will default to `<print>`, otherwise this option will
default to nothing and no `#include` directive will be added.

</div>
