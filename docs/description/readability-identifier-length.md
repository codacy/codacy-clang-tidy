clang-tidy - readability-identifier-length

</div>

# readability-identifier-length

This check finds variables and function parameters whose length are too
short. The desired name length is configurable.

Special cases are supported for loop counters and for exception variable
names.

## Options

The following options are described below:

> - `MinimumVariableNameLength`, `IgnoredVariableNames`
> - `MinimumParameterNameLength`, `IgnoredParameterNames`
> - `MinimumLoopCounterNameLength`, `IgnoredLoopCounterNames`
> - `MinimumExceptionNameLength`, `IgnoredExceptionVariableNames`

<div class="option">

MinimumVariableNameLength

All variables (other than loop counter, exception names and function
parameters) are expected to have at least a length of
<span class="title-ref">MinimumVariableNameLength</span> (default is
<span class="title-ref">3</span>). Setting it to
<span class="title-ref">0</span> or <span class="title-ref">1</span>
disables the check entirely.

``` c++
int i = 42;    // warns that 'i' is too short
```

This check does not have any fix suggestions in the general case since
variable names have semantic value.

</div>

<div class="option">

IgnoredVariableNames

Specifies a regular expression for variable names that are to be
ignored. The default value is empty, thus no names are ignored.

</div>

<div class="option">

MinimumParameterNameLength

All function parameter names are expected to have a length of at least
<span class="title-ref">MinimumParameterNameLength</span> (default is
<span class="title-ref">3</span>). Setting it to
<span class="title-ref">0</span> or <span class="title-ref">1</span>
disables the check entirely.

``` c++
int doubler(int x)   // warns that x is too short
{
   return 2 * x;
}
```

This check does not have any fix suggestions in the general case since
variable names have semantic value.

</div>

<div class="option">

IgnoredParameterNames

Specifies a regular expression for parameters that are to be ignored.
The default value is <span class="title-ref">^\[n\]$</span> for
historical reasons.

</div>

<div class="option">

MinimumLoopCounterNameLength

Loop counter variables are expected to have a length of at least
<span class="title-ref">MinimumLoopCounterNameLength</span> characters
(default is <span class="title-ref">2</span>). Setting it to
<span class="title-ref">0</span> or <span class="title-ref">1</span>
disables the check entirely.

``` c++
// This warns that 'q' is too short.
for (int q = 0; q < size; ++ q) {
   // ...
}
```

</div>

<div class="option">

IgnoredLoopCounterNames

Specifies a regular expression for counter names that are to be ignored.
The default value is <span class="title-ref">^\[ijk\_\]$</span>; the
first three symbols for historical reasons and the last one since it is
frequently used as a "don't care" value, specifically in tools such as
Google Benchmark.

``` c++
// This does not warn by default, for historical reasons.
for (int i = 0; i < size; ++ i) {
    // ...
}
```

</div>

<div class="option">

MinimumExceptionNameLength

Exception clause variables are expected to have a length of at least
<span class="title-ref">MinimumExceptionNameLength</span> (default is
<span class="title-ref">2</span>). Setting it to
<span class="title-ref">0</span> or <span class="title-ref">1</span>
disables the check entirely.

``` c++
try {
    // ...
}
// This warns that 'e' is too short.
catch (const std::exception& x) {
    // ...
}
```

</div>

<div class="option">

IgnoredExceptionVariableNames

Specifies a regular expression for exception variable names that are to
be ignored. The default value is <span class="title-ref">^\[e\]$</span>
mainly for historical reasons.

``` c++
try {
    // ...
}
// This does not warn by default, for historical reasons.
catch (const std::exception& e) {
    // ...
}
```

</div>
