clang-tidy - google-runtime-int

</div>

# google-runtime-int

Finds uses of `short`, `long` and `long long` and suggest replacing them
with `u?intXX(_t)?`.

The corresponding style guide rule:
<https://google.github.io/styleguide/cppguide.html#Integer_Types>.

Corresponding cpplint.py check:
<span class="title-ref">runtime/int</span>.

## Options

<div class="option">

UnsignedTypePrefix

A string specifying the unsigned type prefix. Default is
<span class="title-ref">uint</span>.

</div>

<div class="option">

SignedTypePrefix

A string specifying the signed type prefix. Default is
<span class="title-ref">int</span>.

</div>

<div class="option">

TypeSuffix

A string specifying the type suffix. Default is an empty string.

</div>
