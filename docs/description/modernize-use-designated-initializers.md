clang-tidy - modernize-use-designated-initializers

</div>

# modernize-use-designated-initializers

Finds initializer lists for aggregate types which could be written as
designated initializers instead.

With plain initializer lists, it is very easy to introduce bugs when
adding new fields in the middle of a struct or class type. The same
confusion might arise when changing the order of fields.

C++20 supports the designated initializer syntax for aggregate types. By
applying it, we can always be sure that aggregates are constructed
correctly, because every variable being initialized is referenced by its
name.

Example:

``` 
struct S { int i, j; };
```

is an aggregate type that should be initialized as

``` 
S s{.i = 1, .j = 2};
```

instead of

``` 
S s{1, 2};
```

which could easily become an issue when `i` and `j` are swapped in the
declaration of `S`.

Even when compiling in a language version older than C++20, depending on
your compiler, designated initializers are potentially supported.
Therefore, the check is by default restricted to C99/C++20 and above.
Check out the options `-Wc99-designator` to get support for mixed
designators in initializer list in C and `-Wc++20-designator` for
support of designated initializers in older C++ language modes.

## Options

<div class="option">

IgnoreMacros

The value <span class="title-ref">false</span> specifies that components
of initializer lists expanded from macros are not checked. The default
value is <span class="title-ref">true</span>.

</div>

<div class="option">

IgnoreSingleElementAggregates

The value <span class="title-ref">false</span> specifies that even
initializers for aggregate types with only a single element should be
checked. The default value is <span class="title-ref">true</span>.

</div>

<div class="option">

RestrictToPODTypes

The value <span class="title-ref">true</span> specifies that only Plain
Old Data (POD) types shall be checked. This makes the check applicable
to even older C++ standards. The default value is
<span class="title-ref">false</span>.

</div>

<div class="option">

StrictCStandardCompliance

When set to <span class="title-ref">false</span>, the check will not
restrict itself to C99 and above. The default value is
<span class="title-ref">true</span>.

</div>

<div class="option">

StrictCppStandardCompliance

When set to <span class="title-ref">false</span>, the check will not
restrict itself to C++20 and above. The default value is
<span class="title-ref">true</span>.

</div>
