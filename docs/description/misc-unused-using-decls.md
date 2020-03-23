# misc-unused-using-decls

Finds unused `using` declarations.

Example:

``` c++
namespace n { class C; }
using n::C;  // Never actually used.
```
