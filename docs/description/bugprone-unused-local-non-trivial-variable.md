clang-tidy - bugprone-unused-local-non-trivial-variable

</div>

# bugprone-unused-local-non-trivial-variable

Warns when a local non trivial variable is unused within a function. The
following types of variables are excluded from this check:

- trivial and trivially copyable
- references and pointers
- exception variables in catch clauses
- static or thread local
- structured bindings
- variables with `[[maybe_unused]]` attribute
- name-independent variables

This check can be configured to warn on all non-trivial variables by
setting <span class="title-ref">IncludeTypes</span> to
<span class="title-ref">.\*</span>, and excluding specific types using
<span class="title-ref">ExcludeTypes</span>.

In the this example, <span class="title-ref">my_lock</span> would
generate a warning that it is unused.

``` c++
std::mutex my_lock;
// my_lock local variable is never used
```

In the next example, <span class="title-ref">future2</span> would
generate a warning that it is unused.

``` c++
std::future<MyObject> future1;
std::future<MyObject> future2;
// ...
MyObject foo = future1.get();
// future2 is not used.
```

## Options

<div class="option">

IncludeTypes

Semicolon-separated list of regular expressions matching types of
variables to check. By default the following types are checked:

- <span class="title-ref">::std::.\*mutex</span>
- <span class="title-ref">::std::future</span>
- <span class="title-ref">::std::basic_string</span>
- <span class="title-ref">::std::basic_regex</span>
- <span class="title-ref">::std::basic_istringstream</span>
- <span class="title-ref">::std::basic_stringstream</span>
- <span class="title-ref">::std::bitset</span>
- <span class="title-ref">::std::filesystem::path</span>

</div>

<div class="option">

ExcludeTypes

A semicolon-separated list of regular expressions matching types that
are excluded from the <span class="title-ref">IncludeTypes</span>
matches. By default it is an empty list.

</div>
