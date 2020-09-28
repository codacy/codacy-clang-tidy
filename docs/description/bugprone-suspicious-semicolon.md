bugprone-suspicious-semicolon
=============================

Finds most instances of stray semicolons that unexpectedly alter the
meaning of the code. More specifically, it looks for `if`, `while`,
`for` and `for-range` statements whose body is a single semicolon, and
then analyzes the context of the code (e.g. indentation) in an attempt
to determine whether that is intentional.

    if (x < y);
    {
      x++;
    }

Here the body of the `if` statement consists of only the semicolon at
the end of the first line, and <span class="title-ref">x</span> will be
incremented regardless of the condition.

    while ((line = readLine(file)) != NULL);
      processLine(line);

As a result of this code, <span class="title-ref">processLine()</span>
will only be called once, when the `while` loop with the empty body
exits with <span class="title-ref">line == NULL</span>. The indentation
of the code indicates the intention of the programmer.

    if (x >= y);
    x -= y;

While the indentation does not imply any nesting, there is simply no
valid reason to have an <span class="title-ref">if</span> statement with
an empty body (but it can make sense for a loop). So this check issues a
warning for the code above.

To solve the issue remove the stray semicolon or in case the empty body
is intentional, reflect this using code indentation or put the semicolon
in a new line. For example:

    while (readWhitespace());
      Token t = readNextToken();

Here the second line is indented in a way that suggests that it is meant
to be the body of the <span class="title-ref">while</span> loop - whose
body is in fact empty, because of the semicolon at the end of the first
line.

Either remove the indentation from the second line:

    while (readWhitespace());
    Token t = readNextToken();

... or move the semicolon from the end of the first line to a new line:

    while (readWhitespace())
      ;

      Token t = readNextToken();

In this case the check will assume that you know what you are doing, and
will not raise a warning.
