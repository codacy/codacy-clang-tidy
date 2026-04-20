clang-tidy - modernize-unary-static-assert

</div>

# modernize-unary-static-assert

The check diagnoses any `static_assert` declaration with an empty string
literal and provides a fix-it to replace the declaration with a
single-argument `static_assert` declaration.

The check is only applicable for C++17 and later code.

The following code:

``` c++
void f_textless(int a) {
  static_assert(sizeof(a) <= 10, "");
}
```

is replaced by:

``` c++
void f_textless(int a) {
  static_assert(sizeof(a) <= 10);
}
```
