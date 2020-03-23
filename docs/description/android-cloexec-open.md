android-cloexec-open
====================

A common source of security bugs is code that opens a file without using
the `O_CLOEXEC` flag. Without that flag, an opened sensitive file would
remain open across a fork+exec to a lower-privileged SELinux domain,
leaking that sensitive data. Open-like functions including `open()`,
`openat()`, and `open64()` should include `O_CLOEXEC` in their flags
argument.

Examples:

.. code-block:: c++

open("filename", O\_RDWR); open64("filename", O\_RDWR); openat(0,
"filename", O\_RDWR);

// becomes

open("filename", O\_RDWR \| O\_CLOEXEC); open64("filename", O\_RDWR \|
O\_CLOEXEC); openat(0, "filename", O\_RDWR \| O\_CLOEXEC);
