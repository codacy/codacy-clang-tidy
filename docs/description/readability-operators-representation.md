clang-tidy - readability-operators-representation

</div>

# readability-operators-representation

Enforces consistent token representation for invoked binary, unary and
overloaded operators in C++ code. The check supports both traditional
and alternative representations of operators, such as `&&` and `and`,
`||` and `or`, and so on.

In the realm of C++ programming, developers have the option to choose
between two distinct representations for operators: traditional token
representation and alternative token representation. Traditional tokens
utilize symbols, such as `&&`, `||`, and `!`, while alternative tokens
employ more descriptive words like `and`, `or`, and `not`.

In the following mapping table, a comprehensive list of traditional and
alternative tokens, along with their corresponding representations, is
presented:

<table>
<caption>Token Representation Mapping Table</caption>
<thead>
<tr>
<th>Traditional</th>
<th>Alternative</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>&amp;&amp;</code></td>
<td><code>and</code></td>
</tr>
<tr>
<td><code>&amp;=</code></td>
<td><code>and_eq</code></td>
</tr>
<tr>
<td><code>&amp;</code></td>
<td><code>bitand</code></td>
</tr>
<tr>
<td><code>|</code></td>
<td><code>bitor</code></td>
</tr>
<tr>
<td><code>~</code></td>
<td><code>compl</code></td>
</tr>
<tr>
<td><code>!</code></td>
<td><code>not</code></td>
</tr>
<tr>
<td><code>!=</code></td>
<td><code>not_eq</code></td>
</tr>
<tr>
<td><code>||</code></td>
<td><code>or</code></td>
</tr>
<tr>
<td><code>|=</code></td>
<td><code>or_eq</code></td>
</tr>
<tr>
<td><code>^</code></td>
<td><code>xor</code></td>
</tr>
<tr>
<td><code>^=</code></td>
<td><code>xor_eq</code></td>
</tr>
</tbody>
</table>

## Example

``` c++
// Traditional Token Representation:

if (!a||!b)
{
    // do something
}

// Alternative Token Representation:

if (not a or not b)
{
    // do something
}
```

## Options

Due to the distinct benefits and drawbacks of each representation, the
default configuration doesn't enforce either. Explicit configuration is
needed.

To configure check to enforce Traditional Token Representation for all
operators set options to
<span class="title-ref">&&;&=;&;|;~;!;!=;||;|=;^;^=</span>.

To configure check to enforce Alternative Token Representation for all
operators set options to
<span class="title-ref">and;and_eq;bitand;bitor;compl;not;not_eq;or;or_eq;xor;xor_eq</span>.

Developers do not need to enforce all operators, and can mix the
representations as desired by specifying a semicolon-separated list of
both traditional and alternative tokens in the configuration, such as
<span class="title-ref">and;||;not</span>.

<div class="option">

BinaryOperators

This option allows you to specify a semicolon-separated list of binary
operators for which you want to enforce specific token representation.
The default value is empty string.

</div>

<div class="option">

OverloadedOperators

This option allows you to specify a semicolon-separated list of
overloaded operators for which you want to enforce specific token
representation. The default value is empty string.

</div>
