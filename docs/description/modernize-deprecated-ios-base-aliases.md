clang-tidy - modernize-deprecated-ios-base-aliases

</div>

# modernize-deprecated-ios-base-aliases

Detects usage of the deprecated member types of `std::ios_base` and
replaces those that have a non-deprecated equivalent.

<table>
<thead>
<tr>
<th>Deprecated member type</th>
<th>Replacement</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>std::ios_base::io_state</code></td>
<td><code>std::ios_base::iostate</code></td>
</tr>
<tr>
<td><code>std::ios_base::open_mode</code></td>
<td><code>std::ios_base::openmode</code></td>
</tr>
<tr>
<td><code>std::ios_base::seek_dir</code></td>
<td><code>std::ios_base::seekdir</code></td>
</tr>
<tr>
<td><code>std::ios_base::streamoff</code></td>
<td></td>
</tr>
<tr>
<td><code>std::ios_base::streampos</code></td>
<td></td>
</tr>
</tbody>
</table>
