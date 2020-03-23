# performance-inefficient-string-concatenation

This check warns about the performance overhead arising from
concatenating strings using the `operator+`, for instance:

``` c++
std::string a("Foo"), b("Bar");
a = a + b;
```

Instead of this structure you should use `operator+=` or `std::string`'s
(`std::basic_string`) class member function `append()`. For instance:

``` c++
std::string a("Foo"), b("Baz");
for (int i = 0; i < 20000; ++i) {
    a = a + "Bar" + b;
}
```

Could be rewritten in a greatly more efficient way like:

``` c++
std::string a("Foo"), b("Baz");
for (int i = 0; i < 20000; ++i) {
    a.append("Bar").append(b);
}
```

And this can be rewritten too:

``` c++
void f(const std::string&) {}
std::string a("Foo"), b("Baz");
void g() {
    f(a + "Bar" + b);
}
```

In a slightly more efficient way like:

``` c++
void f(const std::string&) {}
std::string a("Foo"), b("Baz");
void g() {
    f(std::string(a).append("Bar").append(b));
}
```

## Options

<div class="option">

StrictMode

When zero, the check will only check the string usage in `while`, `for`
and `for-range` statements. Default is <span class="title-ref">0</span>.

</div>
