android-cloexec-epoll-create1
=============================

`epoll_create1()` should include `EPOLL_CLOEXEC` in its type argument to
avoid the file descriptor leakage. Without this flag, an opened
sensitive file would remain open across a fork+exec to a
lower-privileged SELinux domain.

Examples:

.. code-block:: c++

epoll\_create1(0);

// becomes

epoll\_create1(EPOLL\_CLOEXEC);
