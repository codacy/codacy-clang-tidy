clang-tidy - misc-unused-alias-decls

</div>

# misc-unused-alias-decls

Finds unused namespace alias declarations.

``` c++
namespace my_namespace {
class C {};
}
namespace unused_alias = ::my_namespace;
```
