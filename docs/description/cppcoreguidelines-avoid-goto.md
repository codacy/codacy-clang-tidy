cppcoreguidelines-avoid-goto
============================

The usage of `goto` for control flow is error prone and should be
replaced with looping constructs. Only forward jumps in nested loops are
accepted.

This check implements
[ES.76](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#es76-avoid-goto)
from the CppCoreGuidelines and
[6.3.1 from High Integrity C++](http://www.codingstandard.com/rule/6-3-1-ensure-that-the-labels-for-a-jump-statement-or-a-switch-condition-appear-later-in-the-same-or-an-enclosing-block/).

For more information on why to avoid programming with `goto` you can
read the famous paper
[A Case against the GO TO Statement.](https://www.cs.utexas.edu/users/EWD/ewd02xx/EWD215.PDF).

The check diagnoses `goto` for backward jumps in every language mode.
These should be replaced with `C/C++` looping constructs.

.. code-block:: c++

// Bad, handwritten for loop. int i = 0; // Jump label for the loop
loop\_start: do\_some\_operation();

if (i &lt; 100) { ++i; goto loop\_start; }

// Better for(int i = 0; i &lt; 100; ++i) do\_some\_operation();

Modern C++ needs `goto` only to jump out of nested loops.

.. code-block:: c++

for(int i = 0; i &lt; 100; ++i) { for(int j = 0; j &lt; 100; ++j) { if
(i \* j &gt; 500) goto early\_exit; } }

early\_exit: some\_operation();

All other uses of `goto` are diagnosed in `C++`.
