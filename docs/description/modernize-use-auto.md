modernize-use-auto
==================

This check is responsible for using the `auto` type specifier for
variable declarations to *improve code readability and maintainability*.
For example:

.. code-block:: c++

std::vector`<int>`{=html}::iterator I = my\_container.begin();

// transforms to:

auto I = my\_container.begin();

The `auto` type specifier will only be introduced in situations where
the variable type matches the type of the initializer expression. In
other words `auto` should deduce the same type that was originally
spelled in the source. However, not every situation should be
transformed:

.. code-block:: c++

int val = 42; InfoStruct &I = SomeObject.getInfo();

// Should not become:

auto val = 42; auto &I = SomeObject.getInfo();

In this example using `auto` for builtins doesn't improve readability.
In other situations it makes the code less self-documenting impairing
readability and maintainability. As a result, `auto` is used only
introduced in specific situations described below.

Iterators
---------

Iterator type specifiers tend to be long and used frequently, especially
in loop constructs. Since the functions generating iterators have a
common format, the type specifier can be replaced without obscuring the
meaning of code while improving readability and maintainability.

.. code-block:: c++

for (std::vector`<int>`{=html}::iterator I = my\_container.begin(), E =
my\_container.end(); I != E; ++I) { }

// becomes

for (auto I = my\_container.begin(), E = my\_container.end(); I != E;
++I) { }

The check will only replace iterator type-specifiers when all of the
following conditions are satisfied:

-   The iterator is for one of the standard container in `std`
    namespace:

    -   `array`
    -   `deque`
    -   `forward_list`
    -   `list`
    -   `vector`
    -   `map`
    -   `multimap`
    -   `set`
    -   `multiset`
    -   `unordered_map`
    -   `unordered_multimap`
    -   `unordered_set`
    -   `unordered_multiset`
    -   `queue`
    -   `priority_queue`
    -   `stack`

-   The iterator is one of the possible iterator types for standard
    containers:

    -   `iterator`
    -   `reverse_iterator`
    -   `const_iterator`
    -   `const_reverse_iterator`

-   In addition to using iterator types directly, typedefs or other ways
    of referring to those types are also allowed. However,
    implementation-specific types for which a type like
    `std::vector<int>::iterator` is itself a typedef will not be
    transformed. Consider the following examples:

.. code-block:: c++

// The following direct uses of iterator types will be transformed.
std::vector`<int>`{=html}::iterator I = MyVec.begin(); { using namespace
std; list`<int>`{=html}::iterator I = MyList.begin(); }

// The type specifier for J would transform to auto since it's a typedef
// to a standard iterator type. typedef std::map\<int,
std::string\>::const\_iterator map\_iterator; map\_iterator J =
MyMap.begin();

// The following implementation-specific iterator type for which //
std::vector`<int>`{=html}::iterator could be a typedef would not be
transformed. \_\_gnu\_cxx::\_\_normal\_iterator\<int\*, std::vector\> K
= MyVec.begin();

-   The initializer for the variable being declared is not a braced
    initializer list. Otherwise, use of `auto` would cause the type of
    the variable to be deduced as `std::initializer_list`.

New expressions
---------------

Frequently, when a pointer is declared and initialized with `new`, the
pointee type is written twice: in the declaration type and in the `new`
expression. In this cases, the declaration type can be replaced with
`auto` improving readability and maintainability.

.. code-block:: c++

TypeName \*my\_pointer = new TypeName(my\_param);

// becomes

auto \*my\_pointer = new TypeName(my\_param);

The check will also replace the declaration type in multiple
declarations, if the following conditions are satisfied:

-   All declared variables have the same type (i.e. all of them are
    pointers to the same type).
-   All declared variables are initialized with a `new` expression.
-   The types of all the new expressions are the same than the pointee
    of the declaration type.

.. code-block:: c++

TypeName *my\_first\_pointer = new TypeName, *my\_second\_pointer = new
TypeName;

// becomes

auto *my\_first\_pointer = new TypeName, *my\_second\_pointer = new
TypeName;

Cast expressions
----------------

Frequently, when a variable is declared and initialized with a cast, the
variable type is written twice: in the declaration type and in the cast
expression. In this cases, the declaration type can be replaced with
`auto` improving readability and maintainability.

.. code-block:: c++

TypeName \*my\_pointer = static\_cast`<TypeName>`{=html}(my\_param);

// becomes

auto \*my\_pointer = static\_cast`<TypeName>`{=html}(my\_param);

The check handles `static_cast`, `dynamic_cast`, `const_cast`,
`reinterpret_cast`, functional casts, C-style casts and function
templates that behave as casts, such as `llvm::dyn_cast`,
`boost::lexical_cast` and `gsl::narrow_cast`. Calls to function
templates are considered to behave as casts if the first template
argument is explicit and is a type, and the function returns that type,
or a pointer or reference to it.

Known Limitations
-----------------

-   If the initializer is an explicit conversion constructor, the check
    will not replace the type specifier even though it would be safe to
    do so.

-   User-defined iterators are not handled at this time.

Options
-------

.. option:: MinTypeNameLength

If the option is set to non-zero (default `5`), the check will ignore
type names having a length less than the option value. The option
affects expressions only, not iterators. Spaces between multi-lexeme
type names (`long int`) are considered as one. If `RemoveStars` option
(see below) is set to non-zero, then `*s` in the type are also counted
as a part of the type name.

.. code-block:: c++

// MinTypeNameLength = 0, RemoveStars=0

int a = static\_cast`<int>`{=html}(foo()); // ---\> auto a = ... //
length(bool *) = 4 bool *b = new bool; // ---\> auto \*b = ... unsigned
c = static\_cast`<unsigned>`{=html}(foo()); // ---\> auto c = ...

// MinTypeNameLength = 5, RemoveStars=0

int a = static\_cast`<int>`{=html}(foo()); // ---\> int a = ... bool b =
static\_cast`<bool>`{=html}(foo()); // ---\> bool b = ... bool *pb =
static\_cast\<bool*\>(foo()); // ---\> bool \*pb = ... unsigned c =
static\_cast`<unsigned>`{=html}(foo()); // ---\> auto c = ... //
length(long `<on-or-more-spaces>`{=html} int) = 8 long int d =
static\_cast`<long int>`{=html}(foo()); // ---\> auto d = ...

// MinTypeNameLength = 5, RemoveStars=1

int a = static\_cast`<int>`{=html}(foo()); // ---\> int a = ... //
length(int \* \* ) = 5 int **pa = static\_cast\<int**\>(foo()); // ---\>
auto pa = ... bool b = static\_cast`<bool>`{=html}(foo()); // ---\> bool
b = ... bool *pb = static\_cast\<bool*\>(foo()); // ---\> auto pb = ...
unsigned c = static\_cast`<unsigned>`{=html}(foo()); // ---\> auto c =
... long int d = static\_cast`<long int>`{=html}(foo()); // ---\> auto d
= ...

.. option:: RemoveStars

If the option is set to non-zero (default is `0`), the check will remove
stars from the non-typedef pointer types when replacing type names with
`auto`. Otherwise, the check will leave stars. For example:

.. code-block:: c++

TypeName *my\_first\_pointer = new TypeName, *my\_second\_pointer = new
TypeName;

// RemoveStars = 0

auto *my\_first\_pointer = new TypeName, *my\_second\_pointer = new
TypeName;

// RemoveStars = 1

auto my\_first\_pointer = new TypeName, my\_second\_pointer = new
TypeName;
