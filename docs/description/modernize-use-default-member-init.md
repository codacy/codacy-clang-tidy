clang-tidy - modernize-use-default-member-init

</div>

# modernize-use-default-member-init

This check converts constructors' member initializers into the new
default member initializers in C++11. Other member initializers that
match the default member initializer are removed. This can reduce
repeated code or allow use of '= default'.

``` c++
struct A {
  A() : i(5), j(10.0) {}
  A(int i) : i(i), j(10.0) {}
  int i;
  double j;
};

// becomes

struct A {
  A() {}
  A(int i) : i(i) {}
  int i{5};
  double j{10.0};
};
```

<div class="note">

<div class="title">

Note

</div>

Only converts member initializers for built-in types, enums, and
pointers. The
<span class="title-ref">readability-redundant-member-init</span> check
will remove redundant member initializers for classes.

</div>

## Options

<div class="option">

UseAssignment

If this option is set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), the check will initialize members
with an assignment. For example:

</div>

``` c++
struct A {
  A() {}
  A(int i) : i(i) {}
  int i = 5;
  double j = 10.0;
};
```

<div class="option">

IgnoreMacros

If this option is set to <span class="title-ref">true</span> (default is
<span class="title-ref">true</span>), the check will not warn about
members declared inside macros.

</div>
