clang-tidy - boost-use-to-string

</div>

# boost-use-to-string

This check finds conversion from integer type like `int` to
`std::string` or `std::wstring` using `boost::lexical_cast`, and replace
it with calls to `std::to_string` and `std::to_wstring`.

It doesn't replace conversion from floating points despite the
`to_string` overloads, because it would change the behaviour.

``` c++
auto str = boost::lexical_cast<std::string>(42);
auto wstr = boost::lexical_cast<std::wstring>(2137LL);

// Will be changed to
auto str = std::to_string(42);
auto wstr = std::to_wstring(2137LL);
```
