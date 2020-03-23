# modernize-use-nullptr

The check converts the usage of null pointer constants (eg. `NULL`, `0`)
to use the new C++11 `nullptr` keyword.

## Example

``` c++
void assignment() {
  char *a = NULL;
  char *b = 0;
  char c = 0;
}

int *ret_ptr() {
  return 0;
}
```

transforms to:

``` c++
void assignment() {
  char *a = nullptr;
  char *b = nullptr;
  char c = 0;
}

int *ret_ptr() {
  return nullptr;
}
```

## Options

<div class="option">

NullMacros

Comma-separated list of macro names that will be transformed along with
`NULL`. By default this check will only replace the `NULL` macro and
will skip any similar user-defined macros.

</div>

### Example

``` c++
#define MY_NULL (void*)0
void assignment() {
  void *p = MY_NULL;
}
```

transforms to:

``` c++
#define MY_NULL NULL
void assignment() {
  int *p = nullptr;
}
```

if the `NullMacros` option is set to `MY_NULL`.
