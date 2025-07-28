clang-tidy - readability-avoid-const-params-in-decls

</div>

# readability-avoid-const-params-in-decls

Checks whether a function declaration has parameters that are top level
`const`.

`const` values in declarations do not affect the signature of a
function, so they should not be put there.

Examples:

``` c++
void f(const string);   // Bad: const is top level.
void f(const string&);  // Good: const is not top level.
```

## Options

<div class="option">

IgnoreMacros

If set to <span class="title-ref">true</span>, the check will not give
warnings inside macros. Default is <span class="title-ref">true</span>.

</div>
