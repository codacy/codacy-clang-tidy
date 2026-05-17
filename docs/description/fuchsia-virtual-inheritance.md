clang-tidy - fuchsia-virtual-inheritance

</div>

# fuchsia-virtual-inheritance

Warns if classes are defined with virtual inheritance.

For example, classes should not be defined with virtual inheritance:

``` c++
class B : public virtual A {};   // warning
```

See the features disallowed in Fuchsia at
<https://fuchsia.googlesource.com/zircon/+/master/docs/cxx.md>
