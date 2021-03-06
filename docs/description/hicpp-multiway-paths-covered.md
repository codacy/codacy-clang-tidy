hicpp-multiway-paths-covered
============================

This check discovers situations where code paths are not fully-covered.
It furthermore suggests using `if` instead of `switch` if the code will
be more clear. The [rule
6.1.2](http://www.codingstandard.com/rule/6-1-2-explicitly-cover-all-paths-through-multi-way-selection-statements/)
and [rule
6.1.4](http://www.codingstandard.com/rule/6-1-4-ensure-that-a-switch-statement-has-at-least-two-case-labels-distinct-from-the-default-label/)
of the High Integrity C++ Coding Standard are enforced.

`if-else if` chains that miss a final `else` branch might lead to
unexpected program execution and be the result of a logical error. If
the missing `else` branch is intended you can leave it empty with a
clarifying comment. This warning can be noisy on some code bases, so it
is disabled by default.

    void f1() {
      int i = determineTheNumber();

       if(i > 0) { 
         // Some Calculation 
       } else if (i < 0) { 
         // Precondition violated or something else. 
       }
       // ...
    }

Similar arguments hold for `switch` statements which do not cover all
possible code paths.

    // The missing default branch might be a logical error. It can be kept empty
    // if there is nothing to do, making it explicit.
    void f2(int i) {
      switch (i) {
      case 0: // something
        break;
      case 1: // something else
        break;
      }
      // All other numbers?
    }

    // Violates this rule as well, but already emits a compiler warning (-Wswitch).
    enum Color { Red, Green, Blue, Yellow };
    void f3(enum Color c) {
      switch (c) {
      case Red: // We can't drive for now.
        break;
      case Green:  // We are allowed to drive.
        break;
      }
      // Other cases missing
    }

The [rule
6.1.4](http://www.codingstandard.com/rule/6-1-4-ensure-that-a-switch-statement-has-at-least-two-case-labels-distinct-from-the-default-label/)
requires every `switch` statement to have at least two `case` labels
other than a <span class="title-ref">default</span> label. Otherwise,
the `switch` could be better expressed with an `if` statement.
Degenerated `switch` statements without any labels are caught as well.

    // Degenerated switch that could be better written as `if`
    int i = 42;
    switch(i) {
      case 1: // do something here
      default: // do somethe else here
    }

    // Should rather be the following:
    if (i == 1) { 
      // do something here 
    }
    else { 
      // do something here 
    }

    // A completely degenerated switch will be diagnosed.
    int i = 42;
    switch(i) {}

Options
-------

WarnOnMissingElse

Boolean flag that activates a warning for missing `else` branches.
Default is <span class="title-ref">0</span>.
