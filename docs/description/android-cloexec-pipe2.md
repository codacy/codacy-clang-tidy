android-cloexec-pipe2
=====================

This checks ensures that pipe2() is called with the O\_CLOEXEC flag. The
check also adds the O\_CLOEXEC flag that marks the file descriptor to be
closed in child processes. Without this flag a sensitive file descriptor
can be leaked to a child process, potentially into a lower-privileged
SELinux domain.

Examples:

    pipe2(pipefd, O_NONBLOCK);

Suggested replacement:

    pipe2(pipefd, O_NONBLOCK | O_CLOEXEC);
