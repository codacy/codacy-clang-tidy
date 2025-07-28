clang-tidy - modernize-use-starts-ends-with

</div>

# modernize-use-starts-ends-with

Checks for common roundabout ways to express `starts_with` and
`ends_with` and suggests replacing with the simpler method when it is
available. Notably, this will work with `std::string` and
`std::string_view`.

Covered scenarios:

<table>
<thead>
<tr>
<th>Expression</th>
<th>Replacement</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>u.find(v) == 0</code></td>
<td><code>u.starts_with(v)</code></td>
</tr>
<tr>
<td><code>u.rfind(v, 0) != 0</code></td>
<td><code>!u.starts_with(v)</code></td>
</tr>
<tr>
<td><code>u.compare(0, v.size(), v) == 0</code></td>
<td><code>u.starts_with(v)</code></td>
</tr>
<tr>
<td><code>u.substr(0, v.size()) == v</code></td>
<td><code>u.starts_with(v)</code></td>
</tr>
<tr>
<td><code>v != u.substr(0, v.size())</code></td>
<td><code>!u.starts_with(v)</code></td>
</tr>
<tr>
<td><code>u.compare(u.size() - v.size(), v.size(), v) == 0</code></td>
<td><code>u.ends_with(v)</code></td>
</tr>
<tr>
<td><code>u.rfind(v) == u.size() - v.size()</code></td>
<td><code>u.ends_with(v)</code></td>
</tr>
</tbody>
</table>
