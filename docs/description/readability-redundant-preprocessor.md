clang-tidy - readability-redundant-preprocessor

</div>

# readability-redundant-preprocessor

Finds potentially redundant preprocessor directives. At the moment the
following cases are detected:

- <span class="title-ref">\#ifdef</span> ..
  <span class="title-ref">\#endif</span> pairs which are nested inside
  an outer pair with the same condition. For example:

``` c++
#ifdef FOO
#ifdef FOO // inner ifdef is considered redundant
void f();
#endif
#endif
```

- Same for <span class="title-ref">\#ifndef</span> ..
  <span class="title-ref">\#endif</span> pairs. For example:

``` c++
#ifndef FOO
#ifndef FOO // inner ifndef is considered redundant
void f();
#endif
#endif
```

- <span class="title-ref">\#ifndef</span> inside an
  <span class="title-ref">\#ifdef</span> with the same condition:

``` c++
#ifdef FOO
#ifndef FOO // inner ifndef is considered redundant
void f();
#endif
#endif
```

- <span class="title-ref">\#ifdef</span> inside an
  <span class="title-ref">\#ifndef</span> with the same condition:

``` c++
#ifndef FOO
#ifdef FOO // inner ifdef is considered redundant
void f();
#endif
#endif
```

- <span class="title-ref">\#if</span> ..
  <span class="title-ref">\#endif</span> pairs which are nested inside
  an outer pair with the same condition. For example:

``` c++
#define FOO 4
#if FOO == 4
#if FOO == 4 // inner if is considered redundant
void f();
#endif
#endif
```
