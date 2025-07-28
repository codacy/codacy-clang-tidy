clang-tidy - readability-braces-around-statements

</div>

# readability-braces-around-statements

<span class="title-ref">google-readability-braces-around-statements</span>
redirects here as an alias for this check.

Checks that bodies of `if` statements and loops (`for`, `do while`, and
`while`) are inside braces.

Before:

``` c++
if (condition)
  statement;
```

After:

``` c++
if (condition) {
  statement;
}
```

## Options

<div class="option">

ShortStatementLines

Defines the minimal number of lines that the statement should have in
order to trigger this check.

The number of lines is counted from the end of condition or initial
keyword (`do`/`else`) until the last line of the inner statement.
Default value <span class="title-ref">0</span> means that braces will be
added to all statements (not having them already).

</div>
