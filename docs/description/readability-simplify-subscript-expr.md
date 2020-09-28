readability-simplify-subscript-expr
===================================

This check simplifies subscript expressions. Currently this covers
calling `.data()` and immediately doing an array subscript operation to
obtain a single element, in which case simply calling `operator[]`
suffice.

Examples:

    std::string s = ...;
    char c = s.data()[i];  // char c = s[i];

Options
-------

Types

The list of type(s) that triggers this check. Default is <span
class="title-ref">::std::basic\_string;::std::basic\_string\_view;::std::vector;::std::array</span>
