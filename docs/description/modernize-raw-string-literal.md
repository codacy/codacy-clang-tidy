modernize-raw-string-literal
============================

This check selectively replaces string literals containing escaped
characters with raw string literals.

Example:

.. code-blocK:: c++

const char *const Quotes{“embedded "quotes"”}; const char *const
Paragraph{“Line one.two.three.”}; const char *const SingleLine{“Single
line.”}; const char *const TrailingSpace{“Look here -&gt; ”}; const char
*const Tab{“One”}; const char *const Bell{“Hello!And welcome!”}; const
char *const Path{“C:\\Program Files\\Vendor\\Application.exe”}; const
char *const RegEx{“\\w\\(\[a-z\]\\)”};

becomes

.. code-block:: c++

const char *const Quotes{R“(embedded”quotes“)”}; const char *const
Paragraph{“Line one.two.three.”}; const char *const SingleLine{“Single
line.”}; const char *const TrailingSpace{“Look here -&gt; ”}; const char
*const Tab{“One”}; const char *const Bell{“Hello!And welcome!”}; const
char *const Path{R“(C:Files.exe)”}; const char *const
RegEx{R“((\[a-z\]))”};

The presence of any of the following escapes can cause the string to be
converted to a raw string literal: `\\`, `\'`, `\"`, `\?`, and octal or
hexadecimal escapes for printable ASCII characters.

A string literal containing only escaped newlines is a common way of
writing lines of text output. Introducing physical newlines with raw
string literals in this case is likely to impede readability. These
string literals are left unchanged.

An escaped horizontal tab, form feed, or vertical tab prevents the
string literal from being converted. The presence of a horizontal tab,
form feed or vertical tab in source code is not visually obvious.
