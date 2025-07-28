clang-tidy - bugprone-easily-swappable-parameters

</div>

# bugprone-easily-swappable-parameters

Finds function definitions where parameters of convertible types follow
each other directly, making call sites prone to calling the function
with swapped (or badly ordered) arguments.

``` c++
void drawPoint(int X, int Y) { /* ... */ }
FILE *open(const char *Dir, const char *Name, Flags Mode) { /* ... */ }
```

A potential call like `drawPoint(-2, 5)` or
`openPath("a.txt", "tmp", Read)` is perfectly legal from the language's
perspective, but might not be what the developer of the function
intended.

More elaborate and type-safe constructs, such as opaque typedefs or
strong types should be used instead, to prevent a mistaken order of
arguments.

``` c++
struct Coord2D { int X; int Y; };
void drawPoint(const Coord2D Pos) { /* ... */ }

FILE *open(const Path &Dir, const Filename &Name, Flags Mode) { /* ... */ }
```

Due to the potentially elaborate refactoring and API-breaking that is
necessary to strengthen the type safety of a project, no automatic
fix-its are offered.

## Options

### Extension/relaxation options

Relaxation (or extension) options can be used to broaden the scope of
the analysis and fine-tune the enabling of more mixes between types.
Some mixes may depend on coding style or preference specific to a
project, however, it should be noted that enabling *all* of these
relaxations model the way of mixing at call sites the most. These
options are expected to make the check report for more functions, and
report longer mixable ranges.

<div class="option">

QualifiersMix

Whether to consider parameters of some *cvr-qualified* `T` and a
differently *cvr-qualified* `T` (i.e. `T` and `const T`, `const T` and
`volatile T`, etc.) mixable between one another. If
<span class="title-ref">false</span>, the check will consider
differently qualified types unmixable.
<span class="title-ref">True</span> turns the warnings on. Defaults to
<span class="title-ref">false</span>.

The following example produces a diagnostic only if
<span class="title-ref">QualifiersMix</span> is enabled:

``` c++
void *memcpy(const void *Destination, void *Source, std::size_t N) { /* ... */ }
```

</div>

<div class="option">

ModelImplicitConversions

Whether to consider parameters of type `T` and `U` mixable if there
exists an implicit conversion from `T` to `U` and `U` to `T`. If
<span class="title-ref">false</span>, the check will not consider
implicitly convertible types for mixability.
<span class="title-ref">True</span> turns warnings for implicit
conversions on. Defaults to <span class="title-ref">true</span>.

The following examples produce a diagnostic only if
<span class="title-ref">ModelImplicitConversions</span> is enabled:

``` c++
void fun(int Int, double Double) { /* ... */ }
void compare(const char *CharBuf, std::string String) { /* ... */ }
```

<div class="note">

<div class="title">

Note

</div>

Changing the qualifiers of an expression's type (e.g. from `int` to
`const int`) is defined as an *implicit conversion* in the C++ Standard.
However, the check separates this decision-making on the mixability of
differently qualified types based on whether
<span class="title-ref">QualifiersMix</span> was enabled.

For example, the following code snippet will only produce a diagnostic
if **both** <span class="title-ref">QualifiersMix</span> and
<span class="title-ref">ModelImplicitConversions</span> are enabled:

``` c++
void fun2(int Int, const double Double) { /* ... */ }
```

</div>

</div>

### Filtering options

Filtering options can be used to lessen the size of the diagnostics
emitted by the checker, whether the aim is to ignore certain constructs
or dampen the noisiness.

<div class="option">

MinimumLength

The minimum length required from an adjacent parameter sequence to be
diagnosed. Defaults to <span class="title-ref">2</span>. Might be any
positive integer greater or equal to <span class="title-ref">2</span>.
If <span class="title-ref">0</span> or <span class="title-ref">1</span>
is given, the default value <span class="title-ref">2</span> will be
used instead.

For example, if <span class="title-ref">3</span> is specified, the
examples above will not be matched.

</div>

<div class="option">

IgnoredParameterNames

The list of parameter **names** that should never be considered part of
a swappable adjacent parameter sequence. The value is a
<span class="title-ref">;</span>-separated list of names. To ignore
unnamed parameters, add <span class="title-ref">""</span> to the list
verbatim (not the empty string, but the two quotes, potentially
escaped!). **This option is case-sensitive!**

By default, the following parameter names, and their Uppercase-initial
variants are ignored: <span class="title-ref">""</span> (unnamed
parameters), <span class="title-ref">iterator</span>,
<span class="title-ref">begin</span>,
<span class="title-ref">end</span>,
<span class="title-ref">first</span>,
<span class="title-ref">last</span>, <span class="title-ref">lhs</span>,
<span class="title-ref">rhs</span>.

</div>

<div class="option">

IgnoredParameterTypeSuffixes

The list of parameter **type name suffixes** that should never be
considered part of a swappable adjacent parameter sequence. Parameters
which type, as written in the source code, end with an element of this
option will be ignored. The value is a
<span class="title-ref">;</span>-separated list of names. **This option
is case-sensitive!**

By default, the following, and their lowercase-initial variants are
ignored: <span class="title-ref">bool</span>,
<span class="title-ref">It</span>,
<span class="title-ref">Iterator</span>,
<span class="title-ref">InputIt</span>,
<span class="title-ref">ForwardIt</span>,
<span class="title-ref">BidirIt</span>,
<span class="title-ref">RandomIt</span>,
<span class="title-ref">random_iterator</span>,
<span class="title-ref">ReverseIt</span>,
<span class="title-ref">reverse_iterator</span>,
<span class="title-ref">reverse_const_iterator</span>,
<span class="title-ref">RandomIt</span>,
<span class="title-ref">random_iterator</span>,
<span class="title-ref">ReverseIt</span>,
<span class="title-ref">reverse_iterator</span>,
<span class="title-ref">reverse_const_iterator</span>,
<span class="title-ref">Const_Iterator</span>,
<span class="title-ref">ConstIterator</span>,
<span class="title-ref">const_reverse_iterator</span>,
<span class="title-ref">ConstReverseIterator</span>. In addition,
<span class="title-ref">\_Bool</span> (but not
<span class="title-ref">\_bool</span>) is also part of the default
value.

</div>

<div class="option">

SuppressParametersUsedTogether

Suppresses diagnostics about parameters that are used together or in a
similar fashion inside the function's body. Defaults to
<span class="title-ref">true</span>. Specifying
<span class="title-ref">false</span> will turn off the heuristics.

Currently, the following heuristics are implemented which will suppress
the warning about the parameter pair involved:

- The parameters are used in the same expression, e.g. `f(a, b)` or
  `a < b`.

- The parameters are further passed to the same function to the same
  parameter of that function, of the same overload. E.g. `f(a, 1)` and
  `f(b, 2)` to some `f(T, int)`.

  <div class="note">

  <div class="title">

  Note

  </div>

  The check does not perform path-sensitive analysis, and as such, "same
  function" in this context means the same function declaration. If the
  same member function of a type on two distinct instances are called
  with the parameters, it will still be regarded as "same function".

  </div>

- The same member field is accessed, or member method is called of the
  two parameters, e.g. `a.foo()` and `b.foo()`.

- Separate `return` statements return either of the parameters on
  different code paths.

</div>

<div class="option">

NamePrefixSuffixSilenceDissimilarityTreshold

The number of characters two parameter names might be different on
*either* the head or the tail end with the rest of the name the same so
that the warning about the two parameters are silenced. Defaults to
<span class="title-ref">1</span>. Might be any positive integer. If
<span class="title-ref">0</span>, the filtering heuristic based on the
parameters' names is turned off.

This option can be used to silence warnings about parameters where the
naming scheme indicates that the order of those parameters do not
matter.

For example, the parameters `LHS` and `RHS` are 1-dissimilar suffixes of
each other: `L` and `R` is the different character, while `HS` is the
common suffix. Similarly, parameters `text1, text2, text3` are
1-dissimilar prefixes of each other, with the numbers at the end being
the dissimilar part. If the value is at least
<span class="title-ref">1</span>, such cases will not be reported.

</div>

## Limitations

**This check is designed to check function signatures!**

The check does not investigate functions that are generated by the
compiler in a context that is only determined from a call site. These
cases include variadic functions, functions in C code that do not have
an argument list, and C++ template instantiations. Most of these cases,
which are otherwise swappable from a caller's standpoint, have no way of
getting "fixed" at the definition point. In the case of C++ templates,
only primary template definitions and explicit specializations are
matched and analyzed.

None of the following cases produce a diagnostic:

``` c++
int printf(const char *Format, ...) { /* ... */ }
int someOldCFunction() { /* ... */ }

template <typename T, typename U>
int add(T X, U Y) { return X + Y };

void theseAreNotWarnedAbout() {
    printf("%d %d\n", 1, 2);   // Two ints passed, they could be swapped.
    someOldCFunction(1, 2, 3); // Similarly, multiple ints passed.

    add(1, 2); // Instantiates 'add<int, int>', but that's not a user-defined function.
}
```

Due to the limitation above, parameters which type are further dependent
upon template instantiations to *prove* that they mix with another
parameter's is not diagnosed.

``` c++
template <typename T>
struct Vector {
  typedef T element_type;
};

// Diagnosed: Explicit instantiation was done by the user, we can prove it
// is the same type.
void instantiated(int A, Vector<int>::element_type B) { /* ... */ }

// Diagnosed: The two parameter types are exactly the same.
template <typename T>
void exact(typename Vector<T>::element_type A,
           typename Vector<T>::element_type B) { /* ... */ }

// Skipped: The two parameters are both 'T' but we cannot prove this
// without actually instantiating.
template <typename T>
void falseNegative(T A, typename Vector<T>::element_type B) { /* ... */ }
```

In the context of *implicit conversions* (when
<span class="title-ref">ModelImplicitConversions</span> is enabled), the
modelling performed by the check warns if the parameters are swappable
and the swapped order matches implicit conversions. It does not model
whether there exists an unrelated third type from which *both*
parameters can be given in a function call. This means that in the
following example, even while `strs()` clearly carries the possibility
to be called with swapped arguments (as long as the arguments are string
literals), will not be warned about.

``` c++
struct String {
    String(const char *Buf);
};

struct StringView {
    StringView(const char *Buf);
    operator const char *() const;
};

// Skipped: Directly swapping expressions of the two type cannot mix.
// (Note: StringView -> const char * -> String would be **two**
// user-defined conversions, which is disallowed by the language.)
void strs(String Str, StringView SV) { /* ... */ }

// Diagnosed: StringView implicitly converts to and from a buffer.
void cStr(StringView SV, const char *Buf() { /* ... */ }
```
