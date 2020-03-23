hicpp-undelegated-constructor
=============================

This check is an alias for
[bugprone-undelegated-constructor](https://clang.llvm.org/extra/clang-tidy/checks/bugprone-undelegated-constructor.html)*.
Partially implements
[rule 12.4.5](http://www.codingstandard.com/rule/12-4-5-use-delegating-constructors-to-reduce-code-duplication/)*
to find misplaced constructor calls inside a constructor.

.. code-block:: c++

struct Ctor { Ctor(); Ctor(int); Ctor(int, int); Ctor(Ctor \*i) { // All
Ctor() calls result in a temporary object Ctor(); // did you intend to
call a delegated constructor? Ctor(0); // did you intend to call a
delegated constructor? Ctor(1, 2); // did you intend to call a delegated
constructor? foo(); } };
