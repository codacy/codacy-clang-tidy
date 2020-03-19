android-cloexec-memfd-create
============================

`memfd_create()` should include `MFD_CLOEXEC` in its type argument to
avoid the file descriptor leakage. Without this flag, an opened
sensitive file would remain open across a fork+exec to a
lower-privileged SELinux domain.

Examples:

.. code-block:: c++

memfd\_create(name, MFD\_ALLOW\_SEALING);

// becomes

memfd\_create(name, MFD\_ALLOW\_SEALING | MFD\_CLOEXEC);
