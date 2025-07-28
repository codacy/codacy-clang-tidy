clang-tidy - modernize-type-traits

</div>

# modernize-type-traits

Converts standard library type traits of the form `traits<...>::type`
and `traits<...>::value` into `traits_t<...>` and `traits_v<...>`
respectively.

For example:

``` c++
std::is_integral<T>::value
std::is_same<int, float>::value
typename std::add_const<T>::type
std::make_signed<unsigned>::type
```

Would be converted into:

``` c++
std::is_integral_v<T>
std::is_same_v<int, float>
std::add_const_t<T>
std::make_signed_t<unsigned>
```

## Options

<div class="option">

IgnoreMacros

If <span class="title-ref">true</span> don't diagnose traits defined in
macros.

Note: Fixes will never be emitted for code inside of macros.

``` c++
#define IS_SIGNED(T) std::is_signed<T>::value
```

Defaults to <span class="title-ref">false</span>.

</div>
