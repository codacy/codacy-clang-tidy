clang-tidy - android-cloexec-accept

</div>

# android-cloexec-accept

The usage of `accept()` is not recommended, it's better to use
`accept4()`. Without this flag, an opened sensitive file descriptor
would remain open across a fork+exec to a lower-privileged SELinux
domain.

Examples:

``` c++
accept(sockfd, addr, addrlen);

// becomes

accept4(sockfd, addr, addrlen, SOCK_CLOEXEC);
```
