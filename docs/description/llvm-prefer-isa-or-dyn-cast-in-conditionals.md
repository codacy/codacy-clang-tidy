llvm-prefer-isa-or-dyn-cast-in-conditionals
===========================================

Looks at conditionals and finds and replaces cases of `cast<>`, which
will assert rather than return a null pointer, and `dyn_cast<>` where
the return value is not captured. Additionally, finds and replaces cases
that match the pattern `var && isa<X>(var)`, where `var` is evaluated
twice.

.. code-block:: c++

// Finds these: if (auto x = cast<X>(y)) {} // is replaced by: if (auto
x = dyn\_cast<X>(y)) {}

if (cast<X>(y)) {} // is replaced by: if (isa<X>(y)) {}

if (dyn\_cast<X>(y)) {} // is replaced by: if (isa<X>(y)) {}

if (var && isa<T>(var)) {} // is replaced by: if
(isa\_and\_nonnull<T>(var.foo())) {}

// Other cases are ignored, e.g.: if (auto f = cast<Z>(y)-&gt;foo()) {}
if (cast<Z>(y)-&gt;foo()) {} if (X.cast(y)) {}
