# readability-redundant-smartptr-get

Find and remove redundant calls to smart pointer's `.get()` method.

Examples:

``` c++
ptr.get()->Foo()  ==>  ptr->Foo()
*ptr.get()  ==>  *ptr
*ptr->get()  ==>  **ptr
if (ptr.get() == nullptr) ... => if (ptr == nullptr) ...
```

<div class="option">

IgnoreMacros

If this option is set to non-zero (default is
<span class="title-ref">1</span>), the check will not warn about calls
inside macros.

</div>
