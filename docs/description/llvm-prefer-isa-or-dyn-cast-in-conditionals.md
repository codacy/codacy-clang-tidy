llvm-prefer-isa-or-dyn-cast-in-conditionals
===========================================

Looks at conditionals and finds and replaces cases of `cast<>`, which
will assert rather than return a null pointer, and `dyn_cast<>` where
the return value is not captured. Additionally, finds and replaces cases
that match the pattern `var && isa<X>(var)`, where `var` is evaluated
twice.

.. code-block:: c++

// Finds these: if (auto x = cast`<X>`{=html}(y)) {} // is replaced by:
if (auto x = dyn\_cast`<X>`{=html}(y)) {}

if (cast[<X>`{=html}(y)) {} // is replaced by: if (isa`](https://clang.llvm.org/extra/clang-tidy/checks/X){=html}(y))
{}

if (dyn\_cast`<X>`{=html}(y)) {} // is replaced by: if
(isa`<X>`{=html}(y)) {}

if (var && isa`<T>`{=html}(var)) {} // is replaced by: if
(isa\_and\_nonnull`<T>`{=html}(var.foo())) {}

// Other cases are ignored, e.g.: if (auto f =
cast[<Z>`{=html}(y)->foo()) {} if (cast`](https://clang.llvm.org/extra/clang-tidy/checks/Z){=html}(y)-\>foo()) {} if
(X.cast(y)) {}
