clang-tidy - modernize-use-using

</div>

# modernize-use-using

The check converts the usage of `typedef` with `using` keyword.

Before:

``` c++
typedef int variable;

class Class{};
typedef void (Class::* MyPtrType)() const;

typedef struct { int a; } R_t, *R_p;
```

After:

``` c++
using variable = int;

class Class{};
using MyPtrType = void (Class::*)() const;

using R_t = struct { int a; };
using R_p = R_t*;
```

The checker ignores <span class="title-ref">typedef</span> within
<span class="title-ref">extern "C" { ... }</span> blocks.

``` c++
extern "C" {
  typedef int InExternC; // Left intact.
}
```

This check requires using C++11 or higher to run.

## Options

<div class="option">

IgnoreMacros

If set to <span class="title-ref">true</span>, the check will not give
warnings inside macros. Default is <span class="title-ref">true</span>.

</div>

<div class="option">

IgnoreExternC

If set to <span class="title-ref">true</span>, the check will not give
warning inside <span class="title-ref">extern "C"\`scope. Default is
\`false</span>

</div>
