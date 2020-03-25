# abseil-string-find-startswith

Checks whether a `std::string::find()` result is compared with 0, and
suggests replacing with `absl::StartsWith()`. This is both a readability
and performance issue.

``` c++
string s = "...";
if (s.find("Hello World") == 0) { /* do something */ }
```

becomes

``` c++
string s = "...";
if (absl::StartsWith(s, "Hello World")) { /* do something */ }
```

## Options

<div class="option">

StringLikeClasses

Semicolon-separated list of names of string-like classes. By default
only `std::basic_string` is considered. The list of methods to
considered is fixed.

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
