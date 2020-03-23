# modernize-use-using

The check converts the usage of `typedef` with `using` keyword.

Before:

``` c++
typedef int variable;

class Class{};
typedef void (Class::* MyPtrType)() const;
```

After:

``` c++
using variable = int;

class Class{};
using MyPtrType = void (Class::*)() const;
```

This check requires using C++11 or higher to run.

## Options

<div class="option">

IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is <span class="title-ref">1</span>.

</div>
