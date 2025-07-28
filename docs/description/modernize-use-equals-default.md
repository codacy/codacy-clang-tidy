clang-tidy - modernize-use-equals-default

</div>

# modernize-use-equals-default

This check replaces default bodies of special member functions with
`= default;`. The explicitly defaulted function declarations enable more
opportunities in optimization, because the compiler might treat
explicitly defaulted functions as trivial.

``` c++
struct A {
  A() {}
  ~A();
};
A::~A() {}

// becomes

struct A {
  A() = default;
  ~A();
};
A::~A() = default;
```

<div class="note">

<div class="title">

Note

</div>

Move-constructor and move-assignment operator are not supported yet.

</div>

## Options

<div class="option">

IgnoreMacros

If set to <span class="title-ref">true</span>, the check will not give
warnings inside macros and will ignore special members with bodies
contain macros or preprocessor directives. Default is
<span class="title-ref">true</span>.

</div>
