readability-redundant-function-ptr-dereference
==============================================

Finds redundant dereferences of a function pointer.

Before:

    int f(int,int);
    int (*p)(int, int) = &f;

    int i = (**p)(10, 50);

After:

    int f(int,int);
    int (*p)(int, int) = &f;

    int i = (*p)(10, 50);
