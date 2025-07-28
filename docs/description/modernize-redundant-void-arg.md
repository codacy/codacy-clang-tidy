clang-tidy - modernize-redundant-void-arg

</div>

# modernize-redundant-void-arg

Find and remove redundant `void` argument lists.

Examples:  
<table>
<thead>
<tr>
<th>Initial code</th>
<th>Code with applied fixes</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>int f(void);</code></td>
<td><code>int f();</code></td>
</tr>
<tr>
<td><code>int (*f(void))(void);</code></td>
<td><code>int (*f())();</code></td>
</tr>
<tr>
<td><code>typedef int (*f_t(void))(void);</code></td>
<td><code>typedef int (*f_t())();</code></td>
</tr>
<tr>
<td><code>void (C::*p)(void);</code></td>
<td><code>void (C::*p)();</code></td>
</tr>
<tr>
<td><code>C::C(void) {}</code></td>
<td><code>C::C() {}</code></td>
</tr>
<tr>
<td><code>C::~C(void) {}</code></td>
<td><code>C::~C() {}</code></td>
</tr>
</tbody>
</table>
