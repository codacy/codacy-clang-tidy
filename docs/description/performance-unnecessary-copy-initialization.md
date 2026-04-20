clang-tidy - performance-unnecessary-copy-initialization

</div>

# performance-unnecessary-copy-initialization

Finds local variable declarations that are initialized using the copy
constructor of a non-trivially-copyable type but it would suffice to
obtain a const reference.

The check is only applied if it is safe to replace the copy by a const
reference. This is the case when the variable is const qualified or when
it is only used as a const, i.e. only const methods or operators are
invoked on it, or it is used as const reference or value argument in
constructors or function calls.

Example:

``` c++
const string& constReference();
void Function() {
  // The warning will suggest making this a const reference.
  const string UnnecessaryCopy = constReference();
}

struct Foo {
  const string& name() const;
};
void Function(const Foo& foo) {
  // The warning will suggest making this a const reference.
  string UnnecessaryCopy1 = foo.name();
  UnnecessaryCopy1.find("bar");

  // The warning will suggest making this a const reference.
  string UnnecessaryCopy2 = UnnecessaryCopy1;
  UnnecessaryCopy2.find("bar");
}
```

## Options

<div class="option">

AllowedTypes

A semicolon-separated list of names of types allowed to be initialized
by copying. Regular expressions are accepted, e.g.
<span class="title-ref">\[Rr\]ef(erence)?$</span> matches every type
with suffix <span class="title-ref">Ref</span>,
<span class="title-ref">ref</span>,
<span class="title-ref">Reference</span> and
<span class="title-ref">reference</span>. The default is empty. If a
name in the list contains the sequence <span class="title-ref">::</span>
it is matched against the qualified typename (i.e.
<span class="title-ref">namespace::Type</span>, otherwise it is matched
against only the type name (i.e. <span class="title-ref">Type</span>).

</div>

<div class="option">

ExcludedContainerTypes

A semicolon-separated list of names of types whose methods are allowed
to return the const reference the variable is copied from. When an
expensive to copy variable is copy initialized by the return value from
a type on this list the check does not trigger. This can be used to
exclude types known to be const incorrect or where the lifetime or
immutability of returned references is not tied to mutations of the
container. An example are view types that don't own the underlying data.
Like for <span class="title-ref">AllowedTypes</span> above, regular
expressions are accepted and the inclusion of
<span class="title-ref">::</span> determines whether the qualified
typename is matched or not.

</div>
