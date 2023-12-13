clang-tidy - modernize-use-equals-delete

</div>

# modernize-use-equals-delete

This check marks unimplemented private special member functions with
`= delete`. To avoid false-positives, this check only applies in a
translation unit that has all other member functions implemented.

``` c++
struct A {
private:
  A(const A&);
  A& operator=(const A&);
};

// becomes

struct A {
private:
  A(const A&) = delete;
  A& operator=(const A&) = delete;
};
```

<div class="option">

IgnoreMacros

If this option is set to non-zero (default is
<span class="title-ref">1</span>), the check will not warn about
functions declared inside macros.

</div>
