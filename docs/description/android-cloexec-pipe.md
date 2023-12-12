clang-tidy - android-cloexec-pipe

</div>

# android-cloexec-pipe

This check detects usage of `pipe()`. Using `pipe()` is not recommended,
`pipe2()` is the suggested replacement. The check also adds the
O_CLOEXEC flag that marks the file descriptor to be closed in child
processes. Without this flag a sensitive file descriptor can be leaked
to a child process, potentially into a lower-privileged SELinux domain.

Examples:

``` c++
pipe(pipefd);
```

Suggested replacement:

``` c++
pipe2(pipefd, O_CLOEXEC);
```
