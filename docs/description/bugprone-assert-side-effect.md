clang-tidy - bugprone-assert-side-effect

</div>

# bugprone-assert-side-effect

Finds `assert()` with side effect.

The condition of `assert()` is evaluated only in debug builds so a
condition with side effect can cause different behavior in debug /
release builds.

## Options

<div class="option">

AssertMacros

A comma-separated list of the names of assert macros to be checked.

</div>

<div class="option">

CheckFunctionCalls

Whether to treat non-const member and non-member functions as they
produce side effects. Disabled by default because it can increase the
number of false positive warnings.

</div>
