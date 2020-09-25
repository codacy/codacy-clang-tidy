readability-redundant-preprocessor
==================================

Finds potentially redundant preprocessor directives. At the moment the
following cases are detected:

-   <span class="title-ref">\#ifdef</span> .. <span
    class="title-ref">\#endif</span> pairs which are nested inside an
    outer pair with the same condition. For example:

<!-- -->

    #ifdef FOO
    #ifdef FOO // inner ifdef is considered redundant
    void f();
    #endif
    #endif

-   Same for <span class="title-ref">\#ifndef</span> .. <span
    class="title-ref">\#endif</span> pairs. For example:

<!-- -->

    #ifndef FOO
    #ifndef FOO // inner ifndef is considered redundant
    void f();
    #endif
    #endif

-   <span class="title-ref">\#ifndef</span> inside an <span
    class="title-ref">\#ifdef</span> with the same condition:

<!-- -->

    #ifdef FOO
    #ifndef FOO // inner ifndef is considered redundant
    void f();
    #endif
    #endif

-   <span class="title-ref">\#ifdef</span> inside an <span
    class="title-ref">\#ifndef</span> with the same condition:

<!-- -->

    #ifndef FOO
    #ifdef FOO // inner ifdef is considered redundant
    void f();
    #endif
    #endif

-   <span class="title-ref">\#if</span> .. <span
    class="title-ref">\#endif</span> pairs which are nested inside an
    outer pair with the same condition. For example:

<!-- -->

    #define FOO 4
    #if FOO == 4
    #if FOO == 4 // inner if is considered redundant
    void f();
    #endif
    #endif
