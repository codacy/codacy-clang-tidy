clang-tidy - readability-redundant-string-init

</div>

# readability-redundant-string-init

Finds unnecessary string initializations.

## Examples

``` c++
// Initializing string with empty string literal is unnecessary.
std::string a = "";
std::string b("");

// becomes

std::string a;
std::string b;
```

## Options

<div class="option">

StringNames

Default is <span class="title-ref">::std::basic_string</span>.

Semicolon-delimited list of class names to apply this check to. By
default <span class="title-ref">::std::basic_string</span> applies to
`std::string` and `std::wstring`. Set to e.g.
<span class="title-ref">::std::basic_string;llvm::StringRef;QString</span>
to perform this check on custom classes.

</div>
