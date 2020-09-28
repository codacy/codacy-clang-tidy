readability-redundant-string-init
=================================

Finds unnecessary string initializations.

Examples
--------

    // Initializing string with empty string literal is unnecessary.
    std::string a = "";
    std::string b("");

    // becomes

    std::string a;
    std::string b;

Options
-------

StringNames

Default is <span class="title-ref">::std::basic\_string</span>.

Semicolon-delimited list of class names to apply this check to. By
default <span class="title-ref">::std::basic\_string</span> applies to
`std::string` and `std::wstring`. Set to e.g. <span
class="title-ref">::std::basic\_string;llvm::StringRef;QString</span> to
perform this check on custom classes.
