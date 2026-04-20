clang-tidy - modernize-use-emplace

</div>

# modernize-use-emplace

The check flags insertions to an STL-style container done by calling the
`push_back`, `push`, or `push_front` methods with an
explicitly-constructed temporary of the container element type. In this
case, the corresponding `emplace` equivalent methods result in less
verbose and potentially more efficient code. Right now the check doesn't
support `insert`. It also doesn't support `insert` functions for
associative containers because replacing `insert` with `emplace` may
result in [speed
regression](https://htmlpreview.github.io/?https://github.com/HowardHinnant/papers/blob/master/insert_vs_emplace.html),
but it might get support with some addition flag in the future.

The `ContainersWithPushBack`, `ContainersWithPush`, and
`ContainersWithPushFront` options are used to specify the container
types that support the `push_back`, `push`, and `push_front` operations
respectively. The default values for these options are as follows:

- `ContainersWithPushBack`: `std::vector`, `std::deque`, and
  `std::list`.
- `ContainersWithPush`: `std::stack`, `std::queue`, and
  `std::priority_queue`.
- `ContainersWithPushFront`: `std::forward_list`, `std::list`, and
  `std::deque`.

This check also reports when an `emplace`-like method is improperly
used, for example using `emplace_back` while also calling a constructor.
This creates a temporary that requires at best a move and at worst a
copy. Almost all `emplace`-like functions in the STL are covered by
this, with `try_emplace` on `std::map` and `std::unordered_map` being
the exception as it behaves slightly differently than all the others.
More containers can be added with the `EmplacyFunctions` option, so long
as the container defines a `value_type` type, and the `emplace`-like
functions construct a `value_type` object.

Before:

``` c++
std::vector<MyClass> v;
v.push_back(MyClass(21, 37));
v.emplace_back(MyClass(21, 37));

std::vector<std::pair<int, int>> w;

w.push_back(std::pair<int, int>(21, 37));
w.push_back(std::make_pair(21L, 37L));
w.emplace_back(std::make_pair(21L, 37L));
```

After:

``` c++
std::vector<MyClass> v;
v.emplace_back(21, 37);
v.emplace_back(21, 37);

std::vector<std::pair<int, int>> w;
w.emplace_back(21, 37);
w.emplace_back(21L, 37L);
w.emplace_back(21L, 37L);
```

By default, the check is able to remove unnecessary `std::make_pair` and
`std::make_tuple` calls from `push_back` calls on containers of
`std::pair` and `std::tuple`. Custom tuple-like types can be modified by
the `TupleTypes` option; custom make functions can be modified by the
`TupleMakeFunctions` option.

The other situation is when we pass arguments that will be converted to
a type inside a container.

Before:

``` c++
std::vector<boost::optional<std::string> > v;
v.push_back("abc");
```

After:

``` c++
std::vector<boost::optional<std::string> > v;
v.emplace_back("abc");
```

In some cases the transformation would be valid, but the code wouldn't
be exception safe. In this case the calls of `push_back` won't be
replaced.

``` c++
std::vector<std::unique_ptr<int>> v;
v.push_back(std::unique_ptr<int>(new int(0)));
auto *ptr = new int(1);
v.push_back(std::unique_ptr<int>(ptr));
```

This is because replacing it with `emplace_back` could cause a leak of
this pointer if `emplace_back` would throw exception before emplacement
(e.g. not enough memory to add a new element).

For more info read item 42 - "Consider emplacement instead of
insertion." of Scott Meyers "Effective Modern C++".

The default smart pointers that are considered are `std::unique_ptr`,
`std::shared_ptr`, `std::auto_ptr`. To specify other smart pointers or
other classes use the `SmartPointers` option.

Check also doesn't fire if any argument of the constructor call would
be:

> - a bit-field (bit-fields can't bind to rvalue/universal reference)
> - a `new` expression (to avoid leak)
> - if the argument would be converted via derived-to-base cast.

This check requires C++11 or higher to run.

## Options

<div class="option">

ContainersWithPushBack

Semicolon-separated list of class names of custom containers that
support `push_back`.

</div>

<div class="option">

ContainersWithPush

Semicolon-separated list of class names of custom containers that
support `push`.

</div>

<div class="option">

ContainersWithPushFront

Semicolon-separated list of class names of custom containers that
support `push_front`.

</div>

<div class="option">

IgnoreImplicitConstructors

When <span class="title-ref">true</span>, the check will ignore
implicitly constructed arguments of `push_back`, e.g.

``` c++
std::vector<std::string> v;
v.push_back("a"); // Ignored when IgnoreImplicitConstructors is `true`.
```

Default is <span class="title-ref">false</span>.

</div>

<div class="option">

SmartPointers

Semicolon-separated list of class names of custom smart pointers.

</div>

<div class="option">

TupleTypes

Semicolon-separated list of `std::tuple`-like class names.

</div>

<div class="option">

TupleMakeFunctions

Semicolon-separated list of `std::make_tuple`-like function names. Those
function calls will be removed from `push_back` calls and turned into
`emplace_back`.

</div>

<div class="option">

EmplacyFunctions

Semicolon-separated list of containers without their template parameters
and some `emplace`-like method of the container. Example:
`vector::emplace_back`. Those methods will be checked for improper use
and the check will report when a temporary is unnecessarily created.

</div>

### Example

``` c++
std::vector<MyTuple<int, bool, char>> x;
x.push_back(MakeMyTuple(1, false, 'x'));
x.emplace_back(MakeMyTuple(1, false, 'x'));
```

transforms to:

``` c++
std::vector<MyTuple<int, bool, char>> x;
x.emplace_back(1, false, 'x');
x.emplace_back(1, false, 'x');
```

when `TupleTypes` is set to `MyTuple`, `TupleMakeFunctions` is set to
`MakeMyTuple`, and `EmplacyFunctions` is set to `vector::emplace_back`.
