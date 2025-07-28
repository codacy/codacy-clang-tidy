clang-tidy - abseil-string-find-startswith

</div>

# abseil-string-find-startswith

Checks whether a `std::string::find()` or `std::string::rfind()` (and
corresponding `std::string_view` methods) result is compared with 0, and
suggests replacing with `absl::StartsWith()`. This is both a readability
and performance issue.

`starts_with` was added as a built-in function on those types in C++20.
If available, prefer enabling `modernize-use-starts-ends-with
<../modernize/use-starts-ends-with>` instead of this check.

``` c++
string s = "...";
if (s.find("Hello World") == 0) { /* do something */ }
if (s.rfind("Hello World", 0) == 0) { /* do something */ }
```

becomes

``` c++
string s = "...";
if (absl::StartsWith(s, "Hello World")) { /* do something */ }
if (absl::StartsWith(s, "Hello World")) { /* do something */ }
```

## Options

<div class="option">

StringLikeClasses

Semicolon-separated list of names of string-like classes. By default
both `std::basic_string` and `std::basic_string_view` are considered.
The list of methods to be considered is fixed.

</div>

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>

<div class="option">

AbseilStringsMatchHeader

The location of Abseil's `strings/match.h`. Defaults to
`absl/strings/match.h`.

</div>
