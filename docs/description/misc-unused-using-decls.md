misc-unused-using-decls
=======================

Finds unused `using` declarations.

Example:

    namespace n { class C; }
    using n::C;  // Never actually used.
