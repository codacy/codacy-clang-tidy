# modernize-use-nodiscard

Adds `[[nodiscard]]` attributes (introduced in C++17) to member
functions in order to highlight at compile time which return values
should not be ignored.

Member functions need to satisfy the following conditions to be
considered by this check:

>   - no `[[nodiscard]]`, `[[noreturn]]`,
>     `__attribute__((warn_unused_result))`,
>     `[[clang::warn_unused_result]]` nor `[[gcc::warn_unused_result]]`
>     attribute,
>   - non-void return type,
>   - non-template return types,
>   - const member function,
>   - non-variadic functions,
>   - no non-const reference parameters,
>   - no pointer parameters,
>   - no template parameters,
>   - no template function parameters,
>   - not be a member of a class with mutable member variables,
>   - no Lambdas,
>   - no conversion functions.

Such functions have no means of altering any state or passing values
other than via the return type. Unless the member functions are altering
state via some external call (e.g. I/O).

## Example

``` c++
bool empty() const;
bool empty(int i) const;
```

transforms to:

``` c++
[[nodiscard] bool empty() const;
[[nodiscard] bool empty(int i) const;
```

## Options

<div class="option">

ReplacementString

Specifies a macro to use instead of `[[nodiscard]]`. This is useful when
maintaining source code that needs to compile with a pre-C++17 compiler.

</div>

### Example

``` c++
bool empty() const;
bool empty(int i) const;
```

transforms to:

``` c++
NO_DISCARD bool empty() const;
NO_DISCARD bool empty(int i) const;
```

if the `ReplacementString` option is set to
<span class="title-ref">NO\_DISCARD</span>.

<div class="note">

<div class="title">

Note

</div>

If the `ReplacementString` is not a C++ attribute, but instead a macro,
then that macro must be defined in scope or the fix-it will not be
applied.

</div>

<div class="note">

<div class="title">

Note

</div>

For alternative `__attribute__` syntax options to mark functions as
`[[nodiscard]]` in non-c++17 source code. See
<https://clang.llvm.org/docs/AttributeReference.html#nodiscard-warn-unused-result>

</div>
