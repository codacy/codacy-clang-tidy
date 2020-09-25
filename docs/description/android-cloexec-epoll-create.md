android-cloexec-epoll-create
============================

The usage of `epoll_create()` is not recommended, it's better to use
`epoll_create1()`, which allows close-on-exec.

Examples:

    epoll_create(size);

    // becomes

    epoll_create1(EPOLL_CLOEXEC);
