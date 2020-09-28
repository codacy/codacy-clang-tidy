hicpp-signed-bitwise
====================

Finds uses of bitwise operations on signed integer types, which may lead
to undefined or implementation defined behaviour.

The according rule is defined in the [High Integrity C++ Standard,
Section
5.6.1](http://www.codingstandard.com/section/5-6-shift-operators/).

Options
-------

IgnorePositiveIntegerLiterals

If this option is set to <span class="title-ref">true</span>, the check
will not warn on bitwise operations with positive integer literals, e.g.
<span class="title-ref">~0</span>, <span class="title-ref">2 &lt;&lt;
1</span>, etc. Default value is <span class="title-ref">false</span>.
