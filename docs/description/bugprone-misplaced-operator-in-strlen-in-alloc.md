bugprone-misplaced-operator-in-strlen-in-alloc
==============================================

Finds cases where `1` is added to the string in the argument to
`strlen()`, `strnlen()`, `strnlen_s()`, `wcslen()`, `wcsnlen()`, and
`wcsnlen_s()` instead of the result and the value is used as an argument
to a memory allocation function (`malloc()`, `calloc()`, `realloc()`,
`alloca()`) or the `new[]` operator in <span
class="title-ref">C++</span>. The check detects error cases even if one
of these functions (except the `new[]` operator) is called by a constant
function pointer. Cases where `1` is added both to the parameter and the
result of the `strlen()`-like function are ignored, as are cases where
the whole addition is surrounded by extra parentheses.

<span class="title-ref">C</span> example code:

    void bad_malloc(char *str) {
      char *c = (char*) malloc(strlen(str + 1));
    }

The suggested fix is to add `1` to the return value of `strlen()` and
not to its argument. In the example above the fix would be

    char *c = (char*) malloc(strlen(str) + 1);

<span class="title-ref">C++</span> example code:

    void bad_new(char *str) {
      char *c = new char[strlen(str + 1)];
    }

As in the <span class="title-ref">C</span> code with the `malloc()`
function, the suggested fix is to add `1` to the return value of
`strlen()` and not to its argument. In the example above the fix would
be

    char *c = new char[strlen(str) + 1];

Example for silencing the diagnostic:

    void bad_malloc(char *str) {
      char *c = (char*) malloc(strlen((str + 1)));
    }
