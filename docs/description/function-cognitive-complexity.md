clang-tidy - readability-function-cognitive-complexity

</div>

# readability-function-cognitive-complexity

Checks function Cognitive Complexity metric.

The metric is implemented as per the [COGNITIVE COMPLEXITY by
SonarSource](https://www.sonarsource.com/docs/CognitiveComplexity.pdf)
specification version 1.2 (19 April 2017).

## Options

<div class="option">

Threshold

Flag functions with Cognitive Complexity exceeding this number. The
default is <span class="title-ref">25</span>.

</div>

<div class="option">

DescribeBasicIncrements

If set to <span class="title-ref">true</span>, then for each function
exceeding the complexity threshold the check will issue additional
diagnostics on every piece of code (loop,
<span class="title-ref">if</span> statement, etc.) which contributes to
that complexity. See also the examples below. Default is
<span class="title-ref">true</span>.

</div>

<div class="option">

IgnoreMacros

If set to <span class="title-ref">true</span>, the check will ignore
code inside macros. Note, that also any macro arguments are ignored,
even if they should count to the complexity. As this might change in the
future, this option isn't guaranteed to be forward-compatible. Default
is <span class="title-ref">false</span>.

</div>

## Building blocks

There are three basic building blocks of a Cognitive Complexity metric:

### Increment

The following structures increase the function's Cognitive Complexity
metric (by <span class="title-ref">1</span>):

- Conditional operators:

  > - `if()`
  > - `else if()`
  > - `else`
  > - `cond ? true : false`

- `switch()`

- Loops:

  > - `for()`
  > - C++11 range-based `for()`
  > - `while()`
  > - `do while()`

- `catch ()`

- `goto LABEL`, `goto *(&&LABEL))`,

- sequences of binary logical operators:

  > - `boolean1 || boolean2`
  > - `boolean1 && boolean2`

### Nesting level

While by itself the nesting level does not change the function's
Cognitive Complexity metric, it is tracked, and is used by the next,
third building block. The following structures increase the nesting
level (by <span class="title-ref">1</span>):

- Conditional operators:

  > - `if()`
  > - `else if()`
  > - `else`
  > - `cond ? true : false`

- `switch()`

- Loops:

  > - `for()`
  > - C++11 range-based `for()`
  > - `while()`
  > - `do while()`

- `catch ()`

- Nested functions:

  > - C++11 Lambda
  > - Nested `class`
  > - Nested `struct`

- GNU statement expression

- Apple Block Declaration

### Nesting increment

This is where the previous basic building block, [Nesting
level](#nesting-level), matters. The following structures increase the
function's Cognitive Complexity metric by the current [Nesting
level](#nesting-level):

- Conditional operators:

  > - `if()`
  > - `cond ? true : false`

- `switch()`

- Loops:

  > - `for()`
  > - C++11 range-based `for()`
  > - `while()`
  > - `do while()`

- `catch ()`

## Examples

The simplest case. This function has Cognitive Complexity of
<span class="title-ref">0</span>.

``` c++
void function0() {}
```

Slightly better example. This function has Cognitive Complexity of
<span class="title-ref">1</span>.

``` c++
int function1(bool var) {
  if(var) // +1, nesting level +1
    return 42;
  return 0;
}
```

Full example. This function has Cognitive Complexity of
<span class="title-ref">3</span>.

``` c++
int function3(bool var1, bool var2) {
  if(var1) { // +1, nesting level +1
    if(var2)  // +2 (1 + current nesting level of 1), nesting level +1
      return 42;
  }

  return 0;
}
```

In the last example, the check will flag
<span class="title-ref">function3</span> if the option Threshold is set
to <span class="title-ref">2</span> or smaller. If the option
DescribeBasicIncrements is set to <span class="title-ref">true</span>,
it will additionally flag the two <span class="title-ref">if</span>
statements with the amounts by which they increase to the complexity of
the function and the current nesting level.

## Limitations

The metric is implemented with two notable exceptions:  
- <span class="title-ref">preprocessor conditionals</span> (`#ifdef`,
  `#if`, `#elif`, `#else`, `#endif`) are not accounted for.
- <span class="title-ref">each method in a recursion cycle</span> is not
  accounted for. It can't be fully implemented, because
  cross-translational-unit analysis would be needed, which is currently
  not possible in clang-tidy.
