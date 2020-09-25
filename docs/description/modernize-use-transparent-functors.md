modernize-use-transparent-functors
==================================

Prefer transparent functors to non-transparent ones. When using
transparent functors, the type does not need to be repeated. The code is
easier to read, maintain and less prone to errors. It is not possible to
introduce unwanted conversions.

    // Non-transparent functor
    std::map<int, std::string, std::greater<int>> s;

    // Transparent functor.
    std::map<int, std::string, std::greater<>> s;

    // Non-transparent functor
    using MyFunctor = std::less<MyType>;

It is not always a safe transformation though. The following case will
be untouched to preserve the semantics.

    // Non-transparent functor
    std::map<const char *, std::string, std::greater<std::string>> s;

Options
-------

SafeMode

If the option is set to non-zero, the check will not diagnose cases
where using a transparent functor cannot be guaranteed to produce
identical results as the original code. The default value for this
option is <span class="title-ref">0</span>.

This check requires using C++14 or higher to run.
