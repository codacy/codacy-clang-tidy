bugprone-argument-comment
=========================

Checks that argument comments match parameter names.

The check understands argument comments in the form
`/*parameter_name=*/` that are placed right before the argument.

    void f(bool foo);

    ...

    f(/*bar=*/true);
    // warning: argument name 'bar' in comment does not match parameter name 'foo'

The check tries to detect typos and suggest automated fixes for them.

Options
-------

StrictMode

When zero (default value), the check will ignore leading and trailing
underscores and case when comparing names -- otherwise they are taken
into account.

IgnoreSingleArgument When true, the check will ignore the single
argument.

CommentBoolLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the boolean literal argument.

Before:

    void foo(bool TurnKey, bool PressButton);

    foo(true, false);

After:

    void foo(bool TurnKey, bool PressButton);

    foo(/*TurnKey=*/true, /*PressButton=*/false);

CommentIntegerLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the integer literal argument.

Before:

    void foo(int MeaningOfLife);

    foo(42);

After:

    void foo(int MeaningOfLife);

    foo(/*MeaningOfLife=*/42);

CommentFloatLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the float/double literal argument.

Before:

    void foo(float Pi);

    foo(3.14159);

After:

    void foo(float Pi);

    foo(/*Pi=*/3.14159);

CommentStringLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the string literal argument.

Before:

    void foo(const char *String);
    void foo(const wchar_t *WideString);

    foo("Hello World");
    foo(L"Hello World");

After:

    void foo(const char *String);
    void foo(const wchar_t *WideString);

    foo(/*String=*/"Hello World");
    foo(/*WideString=*/L"Hello World");

CommentCharacterLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the character literal argument.

Before:

    void foo(char *Character);

    foo('A');

After:

    void foo(char *Character);

    foo(/*Character=*/'A');

CommentUserDefinedLiterals

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the user defined literal argument.

Before:

    void foo(double Distance);

    double operator"" _km(long double);

    foo(402.0_km);

After:

    void foo(double Distance);

    double operator"" _km(long double);

    foo(/*Distance=*/402.0_km);

CommentNullPtrs

When true, the check will add argument comments in the format
`/*ParameterName=*/` right before the nullptr literal argument.

Before:

    void foo(A* Value);

    foo(nullptr);

After:

    void foo(A* Value);

    foo(/*Value=*/nullptr);
