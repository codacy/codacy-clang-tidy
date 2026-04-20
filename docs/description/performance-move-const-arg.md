clang-tidy - performance-move-const-arg

</div>

# performance-move-const-arg

The check warns

- if `std::move()` is called with a constant argument,
- if `std::move()` is called with an argument of a trivially-copyable
  type,
- if the result of `std::move()` is passed as a const reference
  argument.

In all three cases, the check will suggest a fix that removes the
`std::move()`.

Here are examples of each of the three cases:

``` c++
const string s;
return std::move(s);  // Warning: std::move of the const variable has no effect

int x;
return std::move(x);  // Warning: std::move of the variable of a trivially-copyable type has no effect

void f(const string &s);
string s;
f(std::move(s));  // Warning: passing result of std::move as a const reference argument; no move will actually happen
```

## Options

<div class="option">

CheckTriviallyCopyableMove

If <span class="title-ref">true</span>, enables detection of trivially
copyable types that do not have a move constructor. Default is
<span class="title-ref">true</span>.

</div>

<div class="option">

CheckMoveToConstRef

If <span class="title-ref">true</span>, enables detection of
<span class="title-ref">std::move()</span> passed as a const reference
argument. Default is <span class="title-ref">true</span>.

</div>
