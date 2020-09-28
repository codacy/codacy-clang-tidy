modernize-use-using
===================

The check converts the usage of `typedef` with `using` keyword.

Before:

    typedef int variable;

    class Class{};
    typedef void (Class::* MyPtrType)() const;

    typedef struct { int a; } R_t, *R_p;

After:

    using variable = int;

    class Class{};
    using MyPtrType = void (Class::*)() const;

    using R_t = struct { int a; };
    using R_p = R_t*;

This check requires using C++11 or higher to run.

Options
-------

IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is <span class="title-ref">1</span>.
