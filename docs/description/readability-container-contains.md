clang-tidy - readability-container-contains

</div>

# readability-container-contains

Finds usages of `container.count()` and
`container.find() == container.end()` which should be replaced by a call
to the `container.contains()` method.

Whether an element is contained inside a container should be checked
with `contains` instead of `count`/`find` because `contains` conveys the
intent more clearly. Furthermore, for containers which permit multiple
entries per key (`multimap`, `multiset`, ...), `contains` is more
efficient than `count` because `count` has to do unnecessary additional
work.

Examples:

<table>
<thead>
<tr>
<th>Initial expression</th>
<th>Result</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>myMap.find(x) == myMap.end()</code></td>
<td><code>!myMap.contains(x)</code></td>
</tr>
<tr>
<td><code>myMap.find(x) != myMap.end()</code></td>
<td><code>myMap.contains(x)</code></td>
</tr>
<tr>
<td><code>if (myMap.count(x))</code></td>
<td><code>if (myMap.contains(x))</code></td>
</tr>
<tr>
<td><code>bool exists = myMap.count(x)</code></td>
<td><code>bool exists = myMap.contains(x)</code></td>
</tr>
<tr>
<td><code>bool exists = myMap.count(x) &gt; 0</code></td>
<td><code>bool exists = myMap.contains(x)</code></td>
</tr>
<tr>
<td><code>bool exists = myMap.count(x) &gt;= 1</code></td>
<td><code>bool exists = myMap.contains(x)</code></td>
</tr>
<tr>
<td><code>bool missing = myMap.count(x) == 0</code></td>
<td><code>bool missing = !myMap.contains(x)</code></td>
</tr>
</tbody>
</table>

This check will apply to any class that has a `contains` method, notably
including `std::set`, `std::unordered_set`, `std::map`, and
`std::unordered_map` as of C++20, and `std::string` and
`std::string_view` as of C++23.
