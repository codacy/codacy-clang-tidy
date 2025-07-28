clang-tidy - bugprone-lambda-function-name

</div>

# bugprone-lambda-function-name

Checks for attempts to get the name of a function from within a lambda
expression. The name of a lambda is always something like `operator()`,
which is almost never what was intended.

Example:

``` c++
void FancyFunction() {
  [] { printf("Called from %s\n", __func__); }();
  [] { printf("Now called from %s\n", __FUNCTION__); }();
}
```

Output:

    Called from operator()
    Now called from operator()

Likely intended output:

    Called from FancyFunction
    Now called from FancyFunction

## Options

<div class="option">

IgnoreMacros

The value <span class="title-ref">true</span> specifies that attempting
to get the name of a function from within a macro should not be
diagnosed. The default value is <span class="title-ref">false</span>.

</div>
