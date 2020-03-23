# readability-simplify-boolean-expr

Looks for boolean expressions involving boolean constants and simplifies
them to use the appropriate boolean expression directly.

Examples:

<table>
<thead>
<tr class="header">
<th>Initial expression</th>
<th>Result</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><code>if (b == true)</code></td>
<td><blockquote>
<p><code>if (b)</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (b == false)</code></td>
<td><blockquote>
<p><code>if (!b)</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (b &amp;&amp; true)</code></td>
<td><blockquote>
<p><code>if (b)</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (b &amp;&amp; false)</code></td>
<td><blockquote>
<p><code>if (false)</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (b || true)</code></td>
<td><blockquote>
<p><code>if (true)</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (b || false)</code></td>
<td><blockquote>
<p><code>if (b)</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>e ? true : false</code></td>
<td><blockquote>
<p><code>e</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>e ? false : true</code></td>
<td><blockquote>
<p><code>!e</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (true) t(); else f();</code></td>
<td><blockquote>
<p><code>t();</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (false) t(); else f();</code></td>
<td><blockquote>
<p><code>f();</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (e) return true; else return false;</code></td>
<td><blockquote>
<p><code>return e;</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (e) return false; else return true;</code></td>
<td><blockquote>
<p><code>return !e;</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (e) b = true; else b = false;</code></td>
<td><blockquote>
<p><code>b = e;</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (e) b = false; else b = true;</code></td>
<td><blockquote>
<p><code>b = !e;</code></p>
</blockquote></td>
</tr>
<tr class="odd">
<td><code>if (e) return true; return false;</code></td>
<td><blockquote>
<p><code>return e;</code></p>
</blockquote></td>
</tr>
<tr class="even">
<td><code>if (e) return false; return true;</code></td>
<td><blockquote>
<p><code>return !e;</code></p>
</blockquote></td>
</tr>
</tbody>
</table>

  - The resulting expression `e` is modified as follows:
    
    1.  Unnecessary parentheses around the expression are removed.
    2.  Negated applications of `!` are eliminated.
    3.  Negated applications of comparison operators are changed to use
        the opposite condition.
    4.  Implicit conversions of pointers, including pointers to members,
        to `bool` are replaced with explicit comparisons to `nullptr` in
        C++11 or `NULL` in C++98/03.
    5.  Implicit casts to `bool` are replaced with explicit casts to
        `bool`.
    6.  Object expressions with `explicit operator bool` conversion
        operators are replaced with explicit casts to `bool`.
    7.  Implicit conversions of integral types to `bool` are replaced
        with explicit comparisons to `0`.

  - Examples:
    
    1.  The ternary assignment `bool b = (i < 0) ? true : false;` has
        redundant parentheses and becomes `bool b = i < 0;`.
    
    2.  The conditional return `if (!b) return false; return true;` has
        an implied double negation and becomes `return b;`.
    
    3.  The conditional return `if (i < 0) return false; return true;`
        becomes `return i >= 0;`.
        
        The conditional return `if (i != 0) return false; return true;`
        becomes `return i == 0;`.
    
    4.  The conditional return `if (p) return true; return false;` has
        an implicit conversion of a pointer to `bool` and becomes
        `return p != nullptr;`.
        
        The ternary assignment `bool b = (i & 1) ? true : false;` has an
        implicit conversion of `i & 1` to `bool` and becomes `bool b =
        (i & 1) != 0;`.
    
    5.  The conditional return `if (i & 1) return true; else return
        false;` has an implicit conversion of an integer quantity `i
        & 1` to `bool` and becomes `return (i & 1) != 0;`
    
    6.  Given `struct X { explicit operator bool(); };`, and an instance
        `x` of `struct X`, the conditional return `if (x) return true;
        return false;` becomes `return static_cast<bool>(x);`

## Options

<div class="option">

ChainedConditionalReturn

If non-zero, conditional boolean return statements at the end of an
`if/else if` chain will be transformed. Default is
<span class="title-ref">0</span>.

</div>

<div class="option">

ChainedConditionalAssignment

If non-zero, conditional boolean assignments at the end of an `if/else
if` chain will be transformed. Default is
<span class="title-ref">0</span>.

</div>
