android-cloexec-socket
======================

`socket()` should include `SOCK_CLOEXEC` in its type argument to avoid
the file descriptor leakage. Without this flag, an opened sensitive file
would remain open across a fork+exec to a lower-privileged SELinux
domain.

Examples:

.. code-block:: c++

socket(domain, type, SOCK\_STREAM);

// becomes

socket(domain, type, SOCK\_STREAM \| SOCK\_CLOEXEC);
