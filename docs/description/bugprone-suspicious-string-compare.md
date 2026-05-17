clang-tidy - bugprone-suspicious-string-compare

</div>

# bugprone-suspicious-string-compare

Find suspicious usage of runtime string comparison functions. This check
is valid in C and C++.

Checks for calls with implicit comparator and proposed to explicitly add
it.

``` c++
if (strcmp(...))       // Implicitly compare to zero
if (!strcmp(...))      // Won't warn
if (strcmp(...) != 0)  // Won't warn
```

Checks that compare function results (i,e, `strcmp`) are compared to
valid constant. The resulting value is

``` 
<  0    when lower than,
>  0    when greater than,
== 0    when equals.
```

A common mistake is to compare the result to
<span class="title-ref">1</span> or <span class="title-ref">-1</span>.

``` c++
if (strcmp(...) == -1)  // Incorrect usage of the returned value.
```

Additionally, the check warns if the results value is implicitly cast to
a *suspicious* non-integer type. It's happening when the returned value
is used in a wrong context.

``` c++
if (strcmp(...) < 0.)  // Incorrect usage of the returned value.
```

## Options

<div class="option">

WarnOnImplicitComparison

When non-zero, the check will warn on implicit comparison.
<span class="title-ref">1</span> by default.

</div>

<div class="option">

WarnOnLogicalNotComparison

When non-zero, the check will warn on logical not comparison.
<span class="title-ref">0</span> by default.

</div>

<div class="option">

StringCompareLikeFunctions

A string specifying the comma-separated names of the extra string
comparison functions. Default is an empty string. The check will detect
the following string comparison functions:
<span class="title-ref">\_\_builtin_memcmp</span>,
<span class="title-ref">\_\_builtin_strcasecmp</span>,
<span class="title-ref">\_\_builtin_strcmp</span>,
<span class="title-ref">\_\_builtin_strncasecmp</span>,
<span class="title-ref">\_\_builtin_strncmp</span>,
<span class="title-ref">\_mbscmp</span>,
<span class="title-ref">\_mbscmp_l</span>,
<span class="title-ref">\_mbsicmp</span>,
<span class="title-ref">\_mbsicmp_l</span>,
<span class="title-ref">\_mbsnbcmp</span>,
<span class="title-ref">\_mbsnbcmp_l</span>,
<span class="title-ref">\_mbsnbicmp</span>,
<span class="title-ref">\_mbsnbicmp_l</span>,
<span class="title-ref">\_mbsncmp</span>,
<span class="title-ref">\_mbsncmp_l</span>,
<span class="title-ref">\_mbsnicmp</span>,
<span class="title-ref">\_mbsnicmp_l</span>,
<span class="title-ref">\_memicmp</span>,
<span class="title-ref">\_memicmp_l</span>,
<span class="title-ref">\_stricmp</span>,
<span class="title-ref">\_stricmp_l</span>,
<span class="title-ref">\_strnicmp</span>,
<span class="title-ref">\_strnicmp_l</span>,
<span class="title-ref">\_wcsicmp</span>,
<span class="title-ref">\_wcsicmp_l</span>,
<span class="title-ref">\_wcsnicmp</span>,
<span class="title-ref">\_wcsnicmp_l</span>,
<span class="title-ref">lstrcmp</span>,
<span class="title-ref">lstrcmpi</span>,
<span class="title-ref">memcmp</span>,
<span class="title-ref">memicmp</span>,
<span class="title-ref">strcasecmp</span>,
<span class="title-ref">strcmp</span>,
<span class="title-ref">strcmpi</span>,
<span class="title-ref">stricmp</span>,
<span class="title-ref">strncasecmp</span>,
<span class="title-ref">strncmp</span>,
<span class="title-ref">strnicmp</span>,
<span class="title-ref">wcscasecmp</span>,
<span class="title-ref">wcscmp</span>,
<span class="title-ref">wcsicmp</span>,
<span class="title-ref">wcsncmp</span>,
<span class="title-ref">wcsnicmp</span>,
<span class="title-ref">wmemcmp</span>.

</div>
