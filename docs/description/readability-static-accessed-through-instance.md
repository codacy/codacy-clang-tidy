readability-static-accessed-through-instance
============================================

Checks for member expressions that access static members through
instances, and replaces them with uses of the appropriate qualified-id.

Example:

The following code:

    struct C {
      static void foo();
      static int x;
    };

    C *c1 = new C();
    c1->foo();
    c1->x;

is changed to:

    C *c1 = new C();
    C::foo();
    C::x;
