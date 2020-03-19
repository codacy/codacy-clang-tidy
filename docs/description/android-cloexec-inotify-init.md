android-cloexec-inotify-init
============================

The usage of `inotify_init()` is not recommended, it’s better to use
`inotify_init1()`.

Examples:

.. code-block:: c++

inotify\_init();

// becomes

inotify\_init1(IN\_CLOEXEC);
