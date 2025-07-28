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

<div class="option">

IgnoredFunctions

A semicolon-separated list of the names of functions or methods to be
considered as not having side-effects. Regular expressions are accepted,
e.g. <span class="title-ref">\[Rr\]ef(erence)?$</span> matches every
type with suffix <span class="title-ref">Ref</span>,
<span class="title-ref">ref</span>,
<span class="title-ref">Reference</span> and
<span class="title-ref">reference</span>. The default is empty. If a
name in the list contains the sequence <span class="title-ref">::</span>
it is matched against the qualified typename (i.e.
<span class="title-ref">namespace::Type</span>, otherwise it is matched
against only the type name (i.e. <span class="title-ref">Type</span>).

</div>
