android-cloexec-inotify-init1
=============================

`inotify_init1()` should include `IN_CLOEXEC` in its type argument to
avoid the file descriptor leakage. Without this flag, an opened
sensitive file would remain open across a fork+exec to a
lower-privileged SELinux domain.

Examples:

.. code-block:: c++

inotify\_init1(IN\_NONBLOCK);

// becomes

inotify\_init1(IN\_NONBLOCK | IN\_CLOEXEC);
