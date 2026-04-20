clang-tidy - cppcoreguidelines-special-member-functions

</div>

# cppcoreguidelines-special-member-functions

The check finds classes where some but not all of the special member
functions are defined.

By default the compiler defines a copy constructor, copy assignment
operator, move constructor, move assignment operator and destructor. The
default can be suppressed by explicit user-definitions. The relationship
between which functions will be suppressed by definitions of other
functions is complicated and it is advised that all five are defaulted
or explicitly defined.

Note that defining a function with `= delete` is considered to be a
definition.

This check implements
[C.21](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#Rc-five)
from the C++ Core Guidelines.

## Options

<div class="option">

AllowSoleDefaultDtor

When set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), this check will only trigger on
destructors if they are defined and not defaulted.

``` c++
struct A { // This is fine.
  virtual ~A() = default;
};

struct B { // This is not fine.
  ~B() {}
};

struct C {
  // This is not checked, because the destructor might be defaulted in
  // another translation unit.
  ~C();
};
```

</div>

<div class="option">

AllowMissingMoveFunctions

When set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), this check doesn't flag classes
which define no move operations at all. It still flags classes which
define only one of either move constructor or move assignment operator.
With this option enabled, the following class won't be flagged:

``` c++
struct A {
  A(const A&);
  A& operator=(const A&);
  ~A();
};
```

</div>

<div class="option">

AllowMissingMoveFunctionsWhenCopyIsDeleted

When set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), this check doesn't flag classes
which define deleted copy operations but don't define move operations.
This flag is related to Google C++ Style Guide [Copyable and Movable
Types](https://google.github.io/styleguide/cppguide.html#Copyable_Movable_Types).
With this option enabled, the following class won't be flagged:

``` c++
struct A {
  A(const A&) = delete;
  A& operator=(const A&) = delete;
  ~A();
};
```

</div>

<div class="option">

AllowImplicitlyDeletedCopyOrMove

When set to <span class="title-ref">true</span> (default is
<span class="title-ref">false</span>), this check doesn't flag classes
which implicitly delete copy or move operations. With this option
enabled, the following class won't be flagged:

``` c++
struct A : boost::noncopyable {
  ~A() { std::cout << "dtor\n"; }
};
```

</div>
