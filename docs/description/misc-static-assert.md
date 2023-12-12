clang-tidy - misc-static-assert

</div>

# misc-static-assert

<span class="title-ref">cert-dcl03-c</span> redirects here as an alias
for this check.

Replaces `assert()` with `static_assert()` if the condition is
evaluatable at compile time.

The condition of `static_assert()` is evaluated at compile time which is
safer and more efficient.
