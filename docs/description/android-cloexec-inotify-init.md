clang-tidy - android-cloexec-inotify-init

</div>

# android-cloexec-inotify-init

The usage of `inotify_init()` is not recommended, it's better to use
`inotify_init1()`.

Examples:

``` c++
inotify_init();

// becomes

inotify_init1(IN_CLOEXEC);
```
