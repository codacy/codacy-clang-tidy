clang-tidy - hicpp-undelegated-constructor

</div>

<div class="meta"
http-equiv=refresh="5;URL=../bugprone/undelegated-constructor.html">

</div>

# hicpp-undelegated-constructor

This check is an alias for
`bugprone-undelegated-constructor <../bugprone/undelegated-constructor>`.
Partially implements [rule
12.4.5](https://www.perforce.com/resources/qac/high-integrity-cpp-coding-standard/special-member-functions)
to find misplaced constructor calls inside a constructor.

``` c++
struct Ctor {
  Ctor();
  Ctor(int);
  Ctor(int, int);
  Ctor(Ctor *i) {
    // All Ctor() calls result in a temporary object
    Ctor(); // did you intend to call a delegated constructor?
    Ctor(0); // did you intend to call a delegated constructor?
    Ctor(1, 2); // did you intend to call a delegated constructor?
    foo();
  }
};
```
