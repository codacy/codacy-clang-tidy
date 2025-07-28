clang-tidy - misc-throw-by-value-catch-by-reference

</div>

# misc-throw-by-value-catch-by-reference

<span class="title-ref">cert-err09-cpp</span> and
<span class="title-ref">cert-err61-cpp</span> redirect here as aliases
of this check.

Finds violations of the rule "Throw by value, catch by reference"
presented for example in "C++ Coding Standards" by H. Sutter and A.
Alexandrescu, as well as the CERT C++ Coding Standard rule [ERR61-CPP.
Catch exceptions by lvalue
reference](https://wiki.sei.cmu.edu/confluence/display/cplusplus/ERR61-CPP.+Catch+exceptions+by+lvalue+reference).

Exceptions:  
- Throwing string literals will not be flagged despite being a pointer.
  They are not susceptible to slicing and the usage of string literals
  is idiomatic.
- Catching character pointers (`char`, `wchar_t`, unicode character
  types) will not be flagged to allow catching string literals.
- Moved named values will not be flagged as not throwing an anonymous
  temporary. In this case we can be sure that the user knows that the
  object can't be accessed outside catch blocks handling the error.
- Throwing function parameters will not be flagged as not throwing an
  anonymous temporary. This allows helper functions for throwing.
- Re-throwing caught exception variables will not be flagged as not
  throwing an anonymous temporary. Although this can usually be done by
  just writing `throw;` it happens often enough in real code.

## Options

<div class="option">

CheckThrowTemporaries

Triggers detection of violations of the CERT recommendation ERR09-CPP.
Throw anonymous temporaries. Default is
<span class="title-ref">true</span>.

</div>

<div class="option">

WarnOnLargeObject

Also warns for any large, trivial object caught by value. Catching a
large object by value is not dangerous but affects the performance
negatively. The maximum size of an object allowed to be caught without
warning can be set using the <span class="title-ref">MaxSize</span>
option. Default is <span class="title-ref">false</span>.

</div>

<div class="option">

MaxSize

Determines the maximum size of an object allowed to be caught without
warning. Only applicable if `WarnOnLargeObject` is set to
<span class="title-ref">true</span>. If the option is set by the user to
<span class="title-ref">std::numeric_limits\<uint64_t\>::max()</span>
then it reverts to the default value. Default is the size of
<span class="title-ref">size_t</span>.

</div>
