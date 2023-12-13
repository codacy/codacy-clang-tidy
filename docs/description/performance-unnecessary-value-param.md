clang-tidy - performance-unnecessary-value-param

</div>

# performance-unnecessary-value-param

Flags value parameter declarations of expensive to copy types that are
copied for each invocation but it would suffice to pass them by const
reference.

The check is only applied to parameters of types that are expensive to
copy which means they are not trivially copyable or have a non-trivial
copy constructor or destructor.

To ensure that it is safe to replace the value parameter with a const
reference the following heuristic is employed:

1.  the parameter is const qualified;
2.  the parameter is not const, but only const methods or operators are
    invoked on it, or it is used as const reference or value argument in
    constructors or function calls.

Example:

``` c++
void f(const string Value) {
  // The warning will suggest making Value a reference.
}

void g(ExpensiveToCopy Value) {
  // The warning will suggest making Value a const reference.
  Value.ConstMethd();
  ExpensiveToCopy Copy(Value);
}
```

If the parameter is not const, only copied or assigned once and has a
non-trivial move-constructor or move-assignment operator respectively
the check will suggest to move it.

Example:

``` c++
void setValue(string Value) {
  Field = Value;
}
```

Will become:

``` c++
#include <utility>

void setValue(string Value) {
  Field = std::move(Value);
}
```

## Options

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>

<div class="option">

AllowedTypes

A semicolon-separated list of names of types allowed to be passed by
value. Regular expressions are accepted, e.g.
<span class="title-ref">\[Rr\]ef(erence)?$</span> matches every type
with suffix <span class="title-ref">Ref</span>,
<span class="title-ref">ref</span>,
<span class="title-ref">Reference</span> and
<span class="title-ref">reference</span>. The default is empty.

</div>
