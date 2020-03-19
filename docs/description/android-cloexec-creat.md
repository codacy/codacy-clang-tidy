android-cloexec-creat
=====================

The usage of `creat()` is not recommended, it’s better to use `open()`.

Examples:

.. code-block:: c++

int fd = creat(path, mode);

// becomes

int fd = open(path, O\_WRONLY | O\_CREAT | O\_TRUNC | O\_CLOEXEC, mode);
