clang-tidy - misc-uniqueptr-reset-release

</div>

# misc-uniqueptr-reset-release

Find and replace `unique_ptr::reset(release())` with `std::move()`.

Example:

``` c++
std::unique_ptr<Foo> x, y;
x.reset(y.release()); -> x = std::move(y);
```

If `y` is already rvalue, `std::move()` is not added. `x` and `y` can
also be `std::unique_ptr<Foo>*`.

## Options

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>
