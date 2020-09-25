readability-redundant-control-flow
==================================

This check looks for procedures (functions returning no value) with
`return` statements at the end of the function. Such `return` statements
are redundant.

Loop statements (`for`, `while`, `do while`) are checked for redundant
`continue` statements at the end of the loop body.

Examples:

The following function <span class="title-ref">f</span> contains a
redundant `return` statement:

    extern void g();
    void f() {
      g();
      return;
    }

becomes

    extern void g();
    void f() {
      g();
    }

The following function <span class="title-ref">k</span> contains a
redundant `continue` statement:

    void k() {
      for (int i = 0; i < 10; ++i) {
        continue;
      }
    }

becomes

    void k() {
      for (int i = 0; i < 10; ++i) {
      }
    }
