clang-tidy - readability-named-parameter

</div>

# readability-named-parameter

Find functions with unnamed arguments.

The check implements the following rule originating in the Google C++
Style Guide:

<https://google.github.io/styleguide/cppguide.html#Function_Declarations_and_Definitions>

All parameters should have the same name in both the function
declaration and definition. If a parameter is not utilized, its name can
be commented out in a function definition.

``` c++
int doingSomething(int a, int b, int c);

int doingSomething(int a, int b, int /*c*/) {
    // Ok: the third param is not used
    return a + b;
}
```

Corresponding cpplint.py check name:
<span class="title-ref">readability/function</span>.
