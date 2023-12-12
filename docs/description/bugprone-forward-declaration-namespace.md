clang-tidy - bugprone-forward-declaration-namespace

</div>

# bugprone-forward-declaration-namespace

Checks if an unused forward declaration is in a wrong namespace.

The check inspects all unused forward declarations and checks if there
is any declaration/definition with the same name existing, which could
indicate that the forward declaration is in a potentially wrong
namespace.

``` c++
namespace na { struct A; }
namespace nb { struct A {}; }
nb::A a;
// warning : no definition found for 'A', but a definition with the same name
// 'A' found in another namespace 'nb::'
```

This check can only generate warnings, but it can't suggest a fix at
this point.
