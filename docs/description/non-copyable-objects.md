clang-tidy - misc-non-copyable-objects

</div>

# misc-non-copyable-objects

<span class="title-ref">cert-fio38-c</span> redirects here as an alias
for this check.

The check flags dereferences and non-pointer declarations of objects
that are not meant to be passed by value, such as C FILE objects or
POSIX `pthread_mutex_t` objects.

This check corresponds to CERT C++ Coding Standard rule [FIO38-C. Do not
copy a FILE
object](https://www.securecoding.cert.org/confluence/display/c/FIO38-C.+Do+not+copy+a+FILE+object).
