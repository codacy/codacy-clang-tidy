android-cloexec-fopen
=====================

`fopen()` should include `e` in their mode string; so `re` would be
valid. This is equivalent to having set `FD_CLOEXEC on` that descriptor.

Examples:

    fopen("fn", "r");

    // becomes

    fopen("fn", "re");
