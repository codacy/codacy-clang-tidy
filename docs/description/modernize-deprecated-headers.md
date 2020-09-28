modernize-deprecated-headers
============================

Some headers from C library were deprecated in C++ and are no longer
welcome in C++ codebases. Some have no effect in C++. For more details
refer to the C++ 14 Standard \[depr.c.headers\] section.

This check replaces C standard library headers with their C++
alternatives and removes redundant ones.

Important note: the Standard doesn't guarantee that the C++ headers
declare all the same functions in the global namespace. The check in its
current form can break the code that uses library symbols from the
global namespace.

-   <span class="title-ref">&lt;assert.h&gt;</span>
-   <span class="title-ref">&lt;complex.h&gt;</span>
-   <span class="title-ref">&lt;ctype.h&gt;</span>
-   <span class="title-ref">&lt;errno.h&gt;</span>
-   <span class="title-ref">&lt;fenv.h&gt;</span> // deprecated since
    C++11
-   <span class="title-ref">&lt;float.h&gt;</span>
-   <span class="title-ref">&lt;inttypes.h&gt;</span>
-   <span class="title-ref">&lt;limits.h&gt;</span>
-   <span class="title-ref">&lt;locale.h&gt;</span>
-   <span class="title-ref">&lt;math.h&gt;</span>
-   <span class="title-ref">&lt;setjmp.h&gt;</span>
-   <span class="title-ref">&lt;signal.h&gt;</span>
-   <span class="title-ref">&lt;stdarg.h&gt;</span>
-   <span class="title-ref">&lt;stddef.h&gt;</span>
-   <span class="title-ref">&lt;stdint.h&gt;</span>
-   <span class="title-ref">&lt;stdio.h&gt;</span>
-   <span class="title-ref">&lt;stdlib.h&gt;</span>
-   <span class="title-ref">&lt;string.h&gt;</span>
-   <span class="title-ref">&lt;tgmath.h&gt;</span> // deprecated since
    C++11
-   <span class="title-ref">&lt;time.h&gt;</span>
-   <span class="title-ref">&lt;uchar.h&gt;</span> // deprecated since
    C++11
-   <span class="title-ref">&lt;wchar.h&gt;</span>
-   <span class="title-ref">&lt;wctype.h&gt;</span>

If the specified standard is older than C++11 the check will only
replace headers deprecated before C++11, otherwise -- every header that
appeared in the previous list.

These headers don't have effect in C++:

-   <span class="title-ref">&lt;iso646.h&gt;</span>
-   <span class="title-ref">&lt;stdalign.h&gt;</span>
-   <span class="title-ref">&lt;stdbool.h&gt;</span>
