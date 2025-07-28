clang-tidy - cert-oop57-cpp

</div>

# cert-oop57-cpp

> Flags use of the <span class="title-ref">C</span> standard library
> functions `memset`, `memcpy` and `memcmp` and similar derivatives on
> non-trivial types.

## Options

<div class="option">

MemSetNames

Specify extra functions to flag that act similarly to `memset`. Specify
names in a semicolon delimited list. Default is an empty string. The
check will detect the following functions:
<span class="title-ref">memset</span>,
<span class="title-ref">std::memset</span>.

</div>

<div class="option">

MemCpyNames

Specify extra functions to flag that act similarly to `memcpy`. Specify
names in a semicolon delimited list. Default is an empty string. The
check will detect the following functions:
<span class="title-ref">std::memcpy</span>,
<span class="title-ref">memcpy</span>,
<span class="title-ref">std::memmove</span>,
<span class="title-ref">memmove</span>,
<span class="title-ref">std::strcpy</span>,
<span class="title-ref">strcpy</span>,
<span class="title-ref">memccpy</span>,
<span class="title-ref">stpncpy</span>,
<span class="title-ref">strncpy</span>.

</div>

<div class="option">

MemCmpNames

Specify extra functions to flag that act similarly to `memcmp`. Specify
names in a semicolon delimited list. Default is an empty string. The
check will detect the following functions:
<span class="title-ref">std::memcmp</span>,
<span class="title-ref">memcmp</span>,
<span class="title-ref">std::strcmp</span>,
<span class="title-ref">strcmp</span>,
<span class="title-ref">strncmp</span>.

</div>

This check corresponds to the CERT C++ Coding Standard rule [OOP57-CPP.
Prefer special member functions and overloaded operators to C Standard
Library
functions](https://wiki.sei.cmu.edu/confluence/display/cplusplus/OOP57-CPP.+Prefer+special+member+functions+and+overloaded+operators+to+C+Standard+Library+functions).
