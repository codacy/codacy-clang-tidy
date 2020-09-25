modernize-make-shared
=====================

This check finds the creation of `std::shared_ptr` objects by explicitly
calling the constructor and a `new` expression, and replaces it with a
call to `std::make_shared`.

    auto my_ptr = std::shared_ptr<MyPair>(new MyPair(1, 2));

    // becomes

    auto my_ptr = std::make_shared<MyPair>(1, 2);

This check also finds calls to `std::shared_ptr::reset()` with a `new`
expression, and replaces it with a call to `std::make_shared`.

    my_ptr.reset(new MyPair(1, 2));

    // becomes

    my_ptr = std::make_shared<MyPair>(1, 2);

Options
-------

MakeSmartPtrFunction

A string specifying the name of make-shared-ptr function. Default is
<span class="title-ref">std::make\_shared</span>.

MakeSmartPtrFunctionHeader

A string specifying the corresponding header of make-shared-ptr
function. Default is <span class="title-ref">memory</span>.

IncludeStyle

A string specifying which include-style is used, <span
class="title-ref">llvm</span> or <span class="title-ref">google</span>.
Default is <span class="title-ref">llvm</span>.

IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is <span class="title-ref">1</span>.
