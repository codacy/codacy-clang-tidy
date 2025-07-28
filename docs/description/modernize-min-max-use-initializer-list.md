clang-tidy - modernize-min-max-use-initializer-list

</div>

# modernize-min-max-use-initializer-list

Replaces nested `std::min` and `std::max` calls with an initializer list
where applicable.

For instance, consider the following code:

``` cpp
int a = std::max(std::max(i, j), k);
```

The check will transform the above code to:

``` cpp
int a = std::max({i, j, k});
```

# Performance Considerations

While this check simplifies the code and makes it more readable, it may
cause performance degradation for non-trivial types due to the need to
copy objects into the initializer list.

To avoid this, it is recommended to use
<span class="title-ref">std::ref</span> or
<span class="title-ref">std::cref</span> for non-trivial types:

``` cpp
std::string b = std::max({std::ref(i), std::ref(j), std::ref(k)});
```

# Options

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>

<div class="option">

IgnoreNonTrivialTypes

A boolean specifying whether to ignore non-trivial types. Default is
<span class="title-ref">true</span>.

</div>

<div class="option">

IgnoreTrivialTypesOfSizeAbove

An integer specifying the size (in bytes) above which trivial types are
ignored. Default is <span class="title-ref">32</span>.

</div>
