cppcoreguidelines-owning-memory
===============================

This check implements the type-based semantics of [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*),
which allows static analysis on code, that uses raw pointers to handle
resources like dynamic memory, but won't introduce RAII concepts.

The relevant sections in the
[C++ Core Guidelines](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md)
are I.11, C.33, R.3 and GSL.Views The definition of a [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*)
is straight forward

.. code-block:: c++

namespace gsl { template `<typename T>`{=html} owner = T; }

It is therefore simple to introduce the owner even without using an
implementation of the
[Guideline Support Library](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#gsl-guideline-support-library).

All checks are purely type based and not (yet) flow sensitive.

The following examples will demonstrate the correct and incorrect
initializations of owners, assignment is handled the same way. Note that
both `new` and `malloc()`-like resource functions are considered to
produce resources.

.. code-block:: c++

// Creating an owner with factory functions is checked.
gsl::owner\<int*\> function\_that\_returns\_owner() { return
gsl::owner\<int*\>(new int(42)); }

// Dynamic memory must be assigned to an owner int\* Something = new
int(42); // BAD, will be caught gsl::owner\<int*\> Owner = new int(42);
// Good gsl::owner\<int*\> Owner = new int\[42\]; // Good as well

// Returned owner must be assigned to an owner int\* Something =
function\_that\_returns\_owner(); // Bad, factory function
gsl::owner\<int\*\> Owner = function\_that\_returns\_owner(); // Good,
result lands in owner

// Something not a resource or owner should not be assigned to owners
int Stack = 42; gsl::owner\<int\*\> Owned = &Stack; // Bad, not a
resource assigned

In the case of dynamic memory as resource, only [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*)
variables are allowed to be deleted.

.. code-block:: c++

// Example Bad, non-owner as resource handle, will be caught. int\*
NonOwner = new int(42); // First warning here, since new must land in an
owner delete NonOwner; // Second warning here, since only owners are
allowed to be deleted

// Example Good, Ownership correctly stated gsl::owner\<int\*\> Owner =
new int(42); // Good delete Owner; // Good as well, statically enforced,
that only owners get deleted

The check will furthermore ensure, that functions, that expect a
[gsl::owner<T*>` as argument get called with either a `gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*)
or a newly created resource.

.. code-block:: c++

void expects\_owner(gsl::owner\<int\*\> o) { delete o; }

// Bad Code int NonOwner = 42; expects\_owner(&NonOwner); // Bad, will
get caught

// Good Code gsl::owner\<int\*\> Owner = new int(42);
expects\_owner(Owner); // Good expects\_owner(new int(42)); // Good as
well, recognized created resource

// Port legacy code for better resource-safety gsl::owner\<FILE*\> File
= fopen("my\_file.txt", "rw+"); FILE* BadFile =
fopen("another\_file.txt", "w"); // Bad, warned

// ... use the file

fclose(File); // Ok, File is annotated as 'owner\<\>' fclose(BadFile);
// BadFile is not an 'owner\<\>', will be warned

Options
-------

.. option:: LegacyResourceProducers

Semicolon-separated list of fully qualified names of legacy functions
that create resources but cannot introduce `gsl::owner<>`. Defaults to
`::malloc;::aligned_alloc;::realloc;::calloc;::fopen;::freopen;::tmpfile`.

.. option:: LegacyResourceConsumers

Semicolon-separated list of fully qualified names of legacy functions
expecting resource owners as pointer arguments but cannot introduce
`gsl::owner<>`. Defaults to `::free;::realloc;::freopen;::fclose`.

Limitations
-----------

Using [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*) in a typedef or alias is not handled correctly.

.. code-block:: c++

using heap\_int = gsl::owner\<int\*\>; heap\_int allocated = new
int(42); // False positive!

The [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*) is declared as a templated type alias. In template
functions and classes, like in the example below, the information of the
type aliases gets lost. Therefore using [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*) in a heavy
templated code base might lead to false positives.

Known code constructs that do not get diagnosed correctly are:

-   `std::exchange`
-   [std::vector<gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*>)

.. code-block:: c++

// This template function works as expected. Type information doesn't
get lost. template `<typename T>`{=html} void
delete\_owner(gsl::owner\<T\*\> owned\_object) { delete owned\_object;
// Everything alright }

gsl::owner\<int*\> function\_that\_returns\_owner() { return
gsl::owner\<int*\>(new int(42)); }

// Type deduction does not work for auto variables. // This is caught by
the check and will be noted accordingly. auto OwnedObject =
function\_that\_returns\_owner(); // Type of OwnedObject will be int\*

// Problematic function template that looses the typeinformation on
owner template `<typename T>`{=html} void bad\_template\_function(T
some\_object) { // This line will trigger the warning, that a non-owner
is assigned to an owner gsl::owner\<T\*\> new\_owner = some\_object; }

// Calling the function with an owner still yields a false positive.
bad\_template\_function(gsl::owner\<int\*\>(new int(42)));

// The same issue occurs with templated classes like the following.
template `<typename T>`{=html} class OwnedValue { public: const T
getValue() const { return \_val; } private: T \_val; };

// Code, that yields a false positive. OwnedValue\<gsl::owner\<int*\>\>
Owner(new int(42)); // Type deduction yield T -\> int * // False
positive, getValue returns int\* and not gsl::owner\<int*\>
gsl::owner\<int*\> OwnedInt = Owner.getValue();

Another limitation of the current implementation is only the type based
checking. Suppose you have code like the following:

.. code-block:: c++

// Two owners with assigned resources gsl::owner\<int*\> Owner1 = new
int(42); gsl::owner\<int*\> Owner2 = new int(42);

Owner2 = Owner1; // Conceptual Leak of initial resource of Owner2!
Owner1 = nullptr;

The semantic of a [gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*) is mostly like a
[std::unique_ptr<T>`, therefore assignment of two `gsl::owner](https://clang.llvm.org/extra/clang-tidy/checks/T*) is
considered a move, which requires that the resource `Owner2` must have
been released before the assignment. This kind of condition could be
catched in later improvements of this check with flowsensitive analysis.
Currently, the `Clang Static Analyzer` catches this bug for dynamic
memory, but not for general types of resources.
