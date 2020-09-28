modernize-use-bool-literals
===========================

Finds integer literals which are cast to `bool`.

    bool p = 1;
    bool f = static_cast<bool>(1);
    std::ios_base::sync_with_stdio(0);
    bool x = p ? 1 : 0;

    // transforms to

    bool p = true;
    bool f = true;
    std::ios_base::sync_with_stdio(false);
    bool x = p ? true : false;

Options
-------

IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is <span class="title-ref">1</span>.
