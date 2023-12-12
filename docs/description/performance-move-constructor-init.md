clang-tidy - performance-move-constructor-init

</div>

# performance-move-constructor-init

"cert-oop11-cpp" redirects here as an alias for this check.

The check flags user-defined move constructors that have a
ctor-initializer initializing a member or base class through a copy
constructor instead of a move constructor.

## Options

<div class="option">

IncludeStyle

A string specifying which include-style is used,
<span class="title-ref">llvm</span> or
<span class="title-ref">google</span>. Default is
<span class="title-ref">llvm</span>.

</div>
