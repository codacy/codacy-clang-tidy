clang-tidy - readability-simplify-subscript-expr

</div>

# readability-simplify-subscript-expr

This check simplifies subscript expressions. Currently this covers
calling `.data()` and immediately doing an array subscript operation to
obtain a single element, in which case simply calling `operator[]`
suffice.

Examples:

``` c++
std::string s = ...;
char c = s.data()[i];  // char c = s[i];
```

## Options

<div class="option">

Types

The list of type(s) that triggers this check. Default is
<span class="title-ref">::std::basic_string;::std::basic_string_view;::std::vector;::std::array</span>

</div>
