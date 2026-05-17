clang-tidy - hicpp-explicit-conversions

</div>

<div class="meta"
http-equiv=refresh="5;URL=google-explicit-constructor.html">

</div>

# hicpp-explicit-conversions

This check is an alias for
[google-explicit-constructor](https://clang.llvm.org/extra/clang-tidy/checks/google-explicit-constructor.html). Used to
enforce parts of [rule
5.4.1](http://www.codingstandard.com/rule/5-4-1-only-use-casting-forms-static_cast-excl-void-dynamic_cast-or-explicit-constructor-call/).
This check will enforce that constructors and conversion operators are
marked <span class="title-ref">explicit</span>. Other forms of casting
checks are implemented in other places. The following checks can be used
to check for more forms of casting:

- [cppcoreguidelines-pro-type-static-cast-downcast](https://clang.llvm.org/extra/clang-tidy/checks/cppcoreguidelines-pro-type-static-cast-downcast.html)
- [cppcoreguidelines-pro-type-reinterpret-cast](https://clang.llvm.org/extra/clang-tidy/checks/cppcoreguidelines-pro-type-reinterpret-cast.html)
- [cppcoreguidelines-pro-type-const-cast](https://clang.llvm.org/extra/clang-tidy/checks/cppcoreguidelines-pro-type-const-cast.html)
- [cppcoreguidelines-pro-type-cstyle-cast](https://clang.llvm.org/extra/clang-tidy/checks/cppcoreguidelines-pro-type-cstyle-cast.html)
