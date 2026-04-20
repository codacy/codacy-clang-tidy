clang-tidy - readability-suspicious-call-argument

</div>

# readability-suspicious-call-argument

Finds function calls where the arguments passed are provided out of
order, based on the difference between the argument name and the
parameter names of the function.

Given a function call `f(foo, bar);` and a function signature
`void f(T tvar, U uvar)`, the arguments `foo` and `bar` are swapped if
`foo` (the argument name) is more similar to `uvar` (the other
parameter) than `tvar` (the parameter it is currently passed to) **and**
`bar` is more similar to `tvar` than `uvar`.

Warnings might indicate either that the arguments are swapped, or that
the names' cross-similarity might hinder code comprehension.

## Heuristics

The following heuristics are implemented in the check. If **any** of the
enabled heuristics deem the arguments to be provided out of order, a
warning will be issued.

The heuristics themselves are implemented by considering pairs of
strings, and are symmetric, so in the following there is no distinction
on which string is the argument name and which string is the parameter
name.

### Equality

The most trivial heuristic, which compares the two strings for
case-insensitive equality.

### Abbreviation

Common abbreviations can be specified which will deem the strings
similar if the abbreviated and the abbreviation stand together. For
example, if `src` is registered as an abbreviation for `source`, then
the following code example will be warned about.

``` c++
void foo(int source, int x);

foo(b, src);
```

The abbreviations to recognise can be configured with the
`Abbreviations<opt_Abbreviations>` check option. This heuristic is
case-insensitive.

### Prefix

The *prefix* heuristic reports if one of the strings is a sufficiently
long prefix of the other string, e.g. `target` to `targetPtr`. The
similarity percentage is the length ratio of the prefix to the longer
string, in the previous example, it would be <span class="title-ref">6 /
9 = 66.66...</span>%.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">25</span>% dissimilar and
above <span class="title-ref">30</span>% similar. This heuristic is
case-insensitive.

### Suffix

Analogous to the <span class="title-ref">Prefix</span> heuristic. In the
case of `oldValue` and `value` compared, the similarity percentage is
<span class="title-ref">8 / 5 = 62.5</span>%.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">25</span>% dissimilar and
above <span class="title-ref">30</span>% similar. This heuristic is
case-insensitive.

### Substring

The substring heuristic combines the prefix and the suffix heuristic,
and tries to find the *longest common substring* in the two strings
provided. The similarity percentage is the ratio of the found longest
common substring against the *longer* of the two input strings. For
example, given `val` and `rvalue`, the similarity is
<span class="title-ref">3 / 6 = 50</span>%. If no characters are common
in the two string, <span class="title-ref">0</span>%.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">40</span>% dissimilar and
above <span class="title-ref">50</span>% similar. This heuristic is
case-insensitive.

### Levenshtein distance (as <span class="title-ref">Levenshtein</span>)

The [Levenshtein
distance](http://en.wikipedia.org/wiki/Levenshtein_distance) describes
how many single-character changes (additions, changes, or removals) must
be applied to transform one string into another.

The Levenshtein distance is translated into a similarity percentage by
dividing it with the length of the *longer* string, and taking its
complement with regards to <span class="title-ref">100</span>%. For
example, given `something` and `anything`, the distance is
<span class="title-ref">4</span> edits, and the similarity percentage is
<span class="title-ref">100</span>% <span class="title-ref">- 4 / 9 =
55.55...</span>%.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">50</span>% dissimilar and
above <span class="title-ref">66</span>% similar. This heuristic is
case-sensitive.

### Jaro--Winkler distance (as <span class="title-ref">JaroWinkler</span>)

The [Jaro--Winkler
distance](http://en.wikipedia.org/wiki/Jaro–Winkler_distance) is an edit
distance like the Levenshtein distance. It is calculated from the amount
of common characters that are sufficiently close to each other in
position, and to-be-changed characters. The original definition of Jaro
has been extended by Winkler to weigh prefix similarities more. The
similarity percentage is expressed as an average of the common and
non-common characters against the length of both strings.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">75</span>% dissimilar and
above <span class="title-ref">85</span>% similar. This heuristic is
case-insensitive.

### Sørensen--Dice coefficient (as <span class="title-ref">Dice</span>)

The [Sørensen--Dice
coefficient](http://en.wikipedia.org/wiki/Sørensen–Dice_coefficient) was
originally defined to measure the similarity of two sets. Formally, the
coefficient is calculated by dividing <span class="title-ref">2 \*
\#(intersection)</span> with <span class="title-ref">\#(set1) +
\#(set2)</span>, where <span class="title-ref">\#()</span> is the
cardinality function of sets. This metric is applied to strings by
creating bigrams (substring sequences of length 2) of the two strings
and using the set of bigrams for the two strings as the two sets.

This heuristic can be configured with `bounds<opt_Bounds>`. The default
bounds are: below <span class="title-ref">60</span>% dissimilar and
above <span class="title-ref">70</span>% similar. This heuristic is
case-insensitive.

## Options

<div class="option">

MinimumIdentifierNameLength

Sets the minimum required length the argument and parameter names need
to have. Names shorter than this length will be ignored. Defaults to
<span class="title-ref">3</span>.

</div>

<div id="opt_Abbreviations">

<div class="option">

Abbreviations

For the **Abbreviation** heuristic (`see here<abbreviation_heuristic>`),
this option configures the abbreviations in the
<span class="title-ref">"abbreviation=abbreviated_value"</span> format.
The option is a string, with each value joined by
<span class="title-ref">";"</span>.

By default, the following abbreviations are set:

> - <span class="title-ref">addr=address</span>
> - <span class="title-ref">arr=array</span>
> - <span class="title-ref">attr=attribute</span>
> - <span class="title-ref">buf=buffer</span>
> - <span class="title-ref">cl=client</span>
> - <span class="title-ref">cnt=count</span>
> - <span class="title-ref">col=column</span>
> - <span class="title-ref">cpy=copy</span>
> - <span class="title-ref">dest=destination</span>
> - <span class="title-ref">dist=distance</span>
> - <span class="title-ref">dst=distance</span>
> - <span class="title-ref">elem=element</span>
> - <span class="title-ref">hght=height</span>
> - <span class="title-ref">i=index</span>
> - <span class="title-ref">idx=index</span>
> - <span class="title-ref">len=length</span>
> - <span class="title-ref">ln=line</span>
> - <span class="title-ref">lst=list</span>
> - <span class="title-ref">nr=number</span>
> - <span class="title-ref">num=number</span>
> - <span class="title-ref">pos=position</span>
> - <span class="title-ref">ptr=pointer</span>
> - <span class="title-ref">ref=reference</span>
> - <span class="title-ref">src=source</span>
> - <span class="title-ref">srv=server</span>
> - <span class="title-ref">stmt=statement</span>
> - <span class="title-ref">str=string</span>
> - <span class="title-ref">val=value</span>
> - <span class="title-ref">var=variable</span>
> - <span class="title-ref">vec=vector</span>
> - <span class="title-ref">wdth=width</span>

</div>

</div>

The configuration options for each implemented heuristic (see above) is
constructed dynamically. In the following,
<span class="title-ref">\<HeuristicName\></span> refers to one of the
keys from the heuristics implemented.

<div class="option">

\<HeuristicName\>

<span class="title-ref">True</span> or
<span class="title-ref">False</span>, whether a particular heuristic,
such as <span class="title-ref">Equality</span> or
<span class="title-ref">Levenshtein</span> is enabled.

Defaults to <span class="title-ref">True</span> for every heuristic.

</div>

<div id="opt_Bounds">

<div class="option">

\<HeuristicName\>DissimilarBelow, \<HeuristicName\>SimilarAbove

A value between <span class="title-ref">0</span> and
<span class="title-ref">100</span>, expressing a percentage. The bounds
set what percentage of similarity the heuristic must deduce for the two
identifiers to be considered similar or dissimilar by the check.

Given arguments `arg1` and `arg2` passed to `param1` and `param2`,
respectively, the bounds check is performed in the following way: If the
similarity of the currently passed argument order (`arg1` to `param1`)
is **below** the <span class="title-ref">DissimilarBelow</span>
threshold, and the similarity of the suggested swapped order (`arg1` to
`param2`) is **above** the <span class="title-ref">SimilarAbove</span>
threshold, the swap is reported.

For the defaults of each heuristic, `see above<heuristics>`.

</div>

</div>

## Name synthesis

When comparing the argument names and parameter names, the following
logic is used to gather the names for comparison:

Parameter names are the identifiers as written in the source code.

Argument names are:

> - If a variable is passed, the variable's name.
> - If a subsequent function call's return value is used as argument,
>   the called function's name.
> - Otherwise, empty string.

Empty argument or parameter names are ignored by the heuristics.
