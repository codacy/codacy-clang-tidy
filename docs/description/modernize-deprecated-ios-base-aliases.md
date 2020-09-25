modernize-deprecated-ios-base-aliases
=====================================

Detects usage of the deprecated member types of `std::ios_base` and
replaces those that have a non-deprecated equivalent.

<table>
<thead>
<tr class="header">
<th>Deprecated member type</th>
<th>Replacement</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><code>std::ios_base::io_state</code></td>
<td><code>std::ios_base::iostate</code></td>
</tr>
<tr class="even">
<td><code>std::ios_base::open_mode</code></td>
<td><code>std::ios_base::openmode</code></td>
</tr>
<tr class="odd">
<td><p><code>std::ios_base::seek_dir</code> <code>std::ios_base::streamoff</code> <code>std::ios_base::streampos</code></p></td>
<td><p><code>std::ios_base::seekdir</code></p></td>
</tr>
</tbody>
</table>
