clang-tidy - bugprone-unhandled-self-assignment

</div>

# bugprone-unhandled-self-assignment

<span class="title-ref">cert-oop54-cpp</span> redirects here as an alias
for this check. For the CERT alias, the
<span class="title-ref">WarnOnlyIfThisHasSuspiciousField</span> option
is set to <span class="title-ref">0</span>.

Finds user-defined copy assignment operators which do not protect the
code against self-assignment either by checking self-assignment
explicitly or using the copy-and-swap or the copy-and-move method.

By default, this check searches only those classes which have any
pointer or C array field to avoid false positives. In case of a pointer
or a C array, it's likely that self-copy assignment breaks the object if
the copy assignment operator was not written with care.

See also: [OOP54-CPP. Gracefully handle self-copy
assignment](https://wiki.sei.cmu.edu/confluence/display/cplusplus/OOP54-CPP.+Gracefully+handle+self-copy+assignment)

A copy assignment operator must prevent that self-copy assignment ruins
the object state. A typical use case is when the class has a pointer
field and the copy assignment operator first releases the pointed object
and then tries to assign it:

``` c++
class T {
int* p;

public:
  T(const T &rhs) : p(rhs.p ? new int(*rhs.p) : nullptr) {}
  ~T() { delete p; }

  // ...

  T& operator=(const T &rhs) {
    delete p;
    p = new int(*rhs.p);
    return *this;
  }
};
```

There are two common C++ patterns to avoid this problem. The first is
the self-assignment check:

``` c++
class T {
int* p;

public:
  T(const T &rhs) : p(rhs.p ? new int(*rhs.p) : nullptr) {}
  ~T() { delete p; }

  // ...

  T& operator=(const T &rhs) {
    if(this == &rhs)
      return *this;

    delete p;
    p = new int(*rhs.p);
    return *this;
  }
};
```

The second one is the copy-and-swap method when we create a temporary
copy (using the copy constructor) and then swap this temporary object
with `this`:

``` c++
class T {
int* p;

public:
  T(const T &rhs) : p(rhs.p ? new int(*rhs.p) : nullptr) {}
  ~T() { delete p; }

  // ...

  void swap(T &rhs) {
    using std::swap;
    swap(p, rhs.p);
  }

  T& operator=(const T &rhs) {
    T(rhs).swap(*this);
    return *this;
  }
};
```

There is a third pattern which is less common. Let's call it the
copy-and-move method when we create a temporary copy (using the copy
constructor) and then move this temporary object into `this` (needs a
move assignment operator):

``` c++
class T {
int* p;

public:
  T(const T &rhs) : p(rhs.p ? new int(*rhs.p) : nullptr) {}
  ~T() { delete p; }

  // ...

  T& operator=(const T &rhs) {
    T t = rhs;
    *this = std::move(t);
    return *this;
  }

  T& operator=(T &&rhs) {
    p = rhs.p;
    rhs.p = nullptr;
    return *this;
  }
};
```

<div class="option">

WarnOnlyIfThisHasSuspiciousField

When non-zero, the check will warn only if the container class of the
copy assignment operator has any suspicious fields (pointer or C array).
This option is set to <span class="title-ref">1</span> by default.

</div>
