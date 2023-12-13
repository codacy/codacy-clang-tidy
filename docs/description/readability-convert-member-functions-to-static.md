clang-tidy - readability-convert-member-functions-to-static

</div>

# readability-convert-member-functions-to-static

Finds non-static member functions that can be made `static` because the
functions don't use `this`.

After applying modifications as suggested by the check, runnnig the
check again might find more opportunities to mark member functions
`static`.

After making a member function `static`, you might want to run the check
<span class="title-ref">readability-static-accessed-through-instance</span>
to replace calls like `Instance.method()` by `Class::method()`.
