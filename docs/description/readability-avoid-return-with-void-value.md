clang-tidy - readability-avoid-return-with-void-value

</div>

# readability-avoid-return-with-void-value

Finds return statements with `void` values used within functions with
`void` result types.

A function with a `void` return type is intended to perform a task
without producing a return value. Return statements with expressions
could lead to confusion and may miscommunicate the function's intended
behavior.

Example:

``` 
void g();
void f() {
    // ...
    return g();
}
```

In a long function body, the `return` statement suggests that the
function returns a value. However, `return g();` is a combination of two
statements that should be written as

``` 
g();
return;
```

to make clear that `g()` is called and immediately afterwards the
function returns (nothing).

In C, the same issue is detected by the compiler if the `-Wpedantic`
mode is enabled.

## Options

<div class="option">

IgnoreMacros

The value <span class="title-ref">false</span> specifies that return
statements expanded from macros are not checked. The default value is
<span class="title-ref">true</span>.

</div>

<div class="option">

StrictMode

The value <span class="title-ref">false</span> specifies that a direct
return statement shall be excluded from the analysis if it is the only
statement not contained in a block, like `if (cond) return g();`. The
default value is <span class="title-ref">true</span>.

</div>
