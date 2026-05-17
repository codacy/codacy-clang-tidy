clang-tidy - bugprone-argument-comment

</div>

# bugprone-argument-comment

Checks that argument comments match parameter names.

The check understands argument comments in the form
`/*parameter_name=*/` that are placed right before the argument.

``` c++
void f(bool foo);

...

f(/*bar=*/true);
// warning: argument name 'bar' in comment does not match parameter name 'foo'
```

The check tries to detect typos and suggest automated fixes for them.

## Options

<div class="option">

StrictMode

When zero (default value), the check will ignore leading and trailing
underscores and case when comparing names -- otherwise they are taken
into account.

</div>

<div class="option">

IgnoreSingleArgument When true, the check will ignore the single
argument.

</div>

<div class="option">

CommentBoolLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the boolean literal argument.

</div>

Before:

``` c++
void foo(bool TurnKey, bool PressButton);

foo(true, false);
```

After:

``` c++
void foo(bool TurnKey, bool PressButton);

foo(/*TurnKey=*/true, /*PressButton=*/false);
```

<div class="option">

CommentIntegerLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the integer literal argument.

</div>

Before:

``` c++
void foo(int MeaningOfLife);

foo(42);
```

After:

``` c++
void foo(int MeaningOfLife);

foo(/*MeaningOfLife=*/42);
```

<div class="option">

CommentFloatLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the float/double literal argument.

</div>

Before:

``` c++
void foo(float Pi);

foo(3.14159);
```

After:

``` c++
void foo(float Pi);

foo(/*Pi=*/3.14159);
```

<div class="option">

CommentStringLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the string literal argument.

</div>

Before:

``` c++
void foo(const char *String);
void foo(const wchar_t *WideString);

foo("Hello World");
foo(L"Hello World");
```

After:

``` c++
void foo(const char *String);
void foo(const wchar_t *WideString);

foo(/*String=*/"Hello World");
foo(/*WideString=*/L"Hello World");
```

<div class="option">

CommentCharacterLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the character literal argument.

</div>

Before:

``` c++
void foo(char *Character);

foo('A');
```

After:

``` c++
void foo(char *Character);

foo(/*Character=*/'A');
```

<div class="option">

CommentUserDefinedLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the user defined literal argument.

</div>

Before:

``` c++
void foo(double Distance);

double operator"" _km(long double);

foo(402.0_km);
```

After:

``` c++
void foo(double Distance);

double operator"" _km(long double);

foo(/*Distance=*/402.0_km);
```

<div class="option">

CommentNullPtrs

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the nullptr literal argument.

</div>

Before:

``` c++
void foo(A* Value);

foo(nullptr);
```

After:

``` c++
void foo(A* Value);

foo(/*Value=*/nullptr);
```
