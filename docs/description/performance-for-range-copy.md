performance-for-range-copy
==========================

Finds C++11 for ranges where the loop variable is copied in each
iteration but it would suffice to obtain it by const reference.

The check is only applied to loop variables of types that are expensive
to copy which means they are not trivially copyable or have a
non-trivial copy constructor or destructor.

To ensure that it is safe to replace the copy with a const reference the
following heuristic is employed:

1.  The loop variable is const qualified.
2.  The loop variable is not const, but only const methods or operators
    are invoked on it, or it is used as const reference or value
    argument in constructors or function calls.

Options
-------

WarnOnAllAutoCopies

When non-zero, warns on any use of <span class="title-ref">auto</span>
as the type of the range-based for loop variable. Default is <span
class="title-ref">0</span>.

AllowedTypes

A semicolon-separated list of names of types allowed to be copied in
each iteration. Regular expressions are accepted, e.g. <span
class="title-ref">\[Rr\]ef(erence)?$</span> matches every type with
suffix <span class="title-ref">Ref</span>, <span
class="title-ref">ref</span>, <span class="title-ref">Reference</span>
and <span class="title-ref">reference</span>. The default is empty.
