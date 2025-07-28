clang-tidy - readability-identifier-naming

</div>

# readability-identifier-naming

Checks for identifiers naming style mismatch.

This check will try to enforce coding guidelines on the identifiers
naming. It supports one of the following casing types and tries to
convert from one to another if a mismatch is detected

Casing types include:

> - `lower_case`
> - `UPPER_CASE`
> - `camelBack`
> - `CamelCase`
> - `camel_Snake_Back`
> - `Camel_Snake_Case`
> - `aNy_CasE`
> - `Leading_upper_snake_case`

It also supports a fixed prefix and suffix that will be prepended or
appended to the identifiers, regardless of the casing.

Many configuration options are available, in order to be able to create
different rules for different kinds of identifiers. In general, the
rules are falling back to a more generic rule if the specific case is
not configured.

The naming of virtual methods is reported where they occur in the base
class, but not where they are overridden, as it can't be fixed locally
there. This also applies for pseudo-override patterns like CRTP.

`Leading_upper_snake_case` is a naming convention where the first word
is capitalized followed by lower case word(s) separated by underscore(s)
'\_'. Examples include: <span class="title-ref">Cap_snake_case</span>,
<span class="title-ref">Cobra_case</span>,
<span class="title-ref">Foo_bar_baz</span>, and
<span class="title-ref">Master_copy_8gb</span>.

Hungarian notation can be customized using different *HungarianPrefix*
settings. The options and their corresponding values are:

> - `Off` - the default setting
> - `On` - example: `int iVariable`
> - `LowerCase` - example: `int i_Variable`
> - `CamelCase` - example: `int IVariable`

## Options

The following options are described below:

> - `AbstractClassCase`, `AbstractClassPrefix`, `AbstractClassSuffix`,
>   `AbstractClassIgnoredRegexp`, `AbstractClassHungarianPrefix`
> - `AggressiveDependentMemberLookup`
> - `CheckAnonFieldInParent`
> - `ClassCase`, `ClassPrefix`, `ClassSuffix`, `ClassIgnoredRegexp`,
>   `ClassHungarianPrefix`
> - `ClassConstantCase`, `ClassConstantPrefix`, `ClassConstantSuffix`,
>   `ClassConstantIgnoredRegexp`, `ClassConstantHungarianPrefix`
> - `ClassMemberCase`, `ClassMemberPrefix`, `ClassMemberSuffix`,
>   `ClassMemberIgnoredRegexp`, `ClassMemberHungarianPrefix`
> - `ClassMethodCase`, `ClassMethodPrefix`, `ClassMethodSuffix`,
>   `ClassMethodIgnoredRegexp`
> - `ConceptCase`, `ConceptPrefix`, `ConceptSuffix`,
>   `ConceptIgnoredRegexp`
> - `ConstantCase`, `ConstantPrefix`, `ConstantSuffix`,
>   `ConstantIgnoredRegexp`, `ConstantHungarianPrefix`
> - `ConstantMemberCase`, `ConstantMemberPrefix`,
>   `ConstantMemberSuffix`, `ConstantMemberIgnoredRegexp`,
>   `ConstantMemberHungarianPrefix`
> - `ConstantParameterCase`, `ConstantParameterPrefix`,
>   `ConstantParameterSuffix`, `ConstantParameterIgnoredRegexp`,
>   `ConstantParameterHungarianPrefix`
> - `ConstantPointerParameterCase`, `ConstantPointerParameterPrefix`,
>   `ConstantPointerParameterSuffix`,
>   `ConstantPointerParameterIgnoredRegexp`,
>   `ConstantPointerParameterHungarianPrefix`
> - `ConstexprFunctionCase`, `ConstexprFunctionPrefix`,
>   `ConstexprFunctionSuffix`, `ConstexprFunctionIgnoredRegexp`
> - `ConstexprMethodCase`, `ConstexprMethodPrefix`,
>   `ConstexprMethodSuffix`, `ConstexprMethodIgnoredRegexp`
> - `ConstexprVariableCase`, `ConstexprVariablePrefix`,
>   `ConstexprVariableSuffix`, `ConstexprVariableIgnoredRegexp`,
>   `ConstexprVariableHungarianPrefix`
> - `EnumCase`, `EnumPrefix`, `EnumSuffix`, `EnumIgnoredRegexp`
> - `EnumConstantCase`, `EnumConstantPrefix`, `EnumConstantSuffix`,
>   `EnumConstantIgnoredRegexp`, `EnumConstantHungarianPrefix`
> - `FunctionCase`, `FunctionPrefix`, `FunctionSuffix`,
>   `FunctionIgnoredRegexp`
> - `GetConfigPerFile`
> - `GlobalConstantCase`, `GlobalConstantPrefix`,
>   `GlobalConstantSuffix`, `GlobalConstantIgnoredRegexp`,
>   `GlobalConstantHungarianPrefix`
> - `GlobalConstantPointerCase`, `GlobalConstantPointerPrefix`,
>   `GlobalConstantPointerSuffix`, `GlobalConstantPointerIgnoredRegexp`,
>   `GlobalConstantPointerHungarianPrefix`
> - `GlobalFunctionCase`, `GlobalFunctionPrefix`,
>   `GlobalFunctionSuffix`, `GlobalFunctionIgnoredRegexp`
> - `GlobalPointerCase`, `GlobalPointerPrefix`, `GlobalPointerSuffix`,
>   `GlobalPointerIgnoredRegexp`, `GlobalPointerHungarianPrefix`
> - `GlobalVariableCase`, `GlobalVariablePrefix`,
>   `GlobalVariableSuffix`, `GlobalVariableIgnoredRegexp`,
>   `GlobalVariableHungarianPrefix`
> - `IgnoreMainLikeFunctions`
> - `InlineNamespaceCase`, `InlineNamespacePrefix`,
>   `InlineNamespaceSuffix`, `InlineNamespaceIgnoredRegexp`
> - `LocalConstantCase`, `LocalConstantPrefix`, `LocalConstantSuffix`,
>   `LocalConstantIgnoredRegexp`, `LocalConstantHungarianPrefix`
> - `LocalConstantPointerCase`, `LocalConstantPointerPrefix`,
>   `LocalConstantPointerSuffix`, `LocalConstantPointerIgnoredRegexp`,
>   `LocalConstantPointerHungarianPrefix`
> - `LocalPointerCase`, `LocalPointerPrefix`, `LocalPointerSuffix`,
>   `LocalPointerIgnoredRegexp`, `LocalPointerHungarianPrefix`
> - `LocalVariableCase`, `LocalVariablePrefix`, `LocalVariableSuffix`,
>   `LocalVariableIgnoredRegexp`, `LocalVariableHungarianPrefix`
> - `MacroDefinitionCase`, `MacroDefinitionPrefix`,
>   `MacroDefinitionSuffix`, `MacroDefinitionIgnoredRegexp`
> - `MemberCase`, `MemberPrefix`, `MemberSuffix`, `MemberIgnoredRegexp`,
>   `MemberHungarianPrefix`
> - `MethodCase`, `MethodPrefix`, `MethodSuffix`, `MethodIgnoredRegexp`
> - `NamespaceCase`, `NamespacePrefix`, `NamespaceSuffix`,
>   `NamespaceIgnoredRegexp`
> - `ParameterCase`, `ParameterPrefix`, `ParameterSuffix`,
>   `ParameterIgnoredRegexp`, `ParameterHungarianPrefix`
> - `ParameterPackCase`, `ParameterPackPrefix`, `ParameterPackSuffix`,
>   `ParameterPackIgnoredRegexp`
> - `PointerParameterCase`, `PointerParameterPrefix`,
>   `PointerParameterSuffix`, `PointerParameterIgnoredRegexp`,
>   `PointerParameterHungarianPrefix`
> - `PrivateMemberCase`, `PrivateMemberPrefix`, `PrivateMemberSuffix`,
>   `PrivateMemberIgnoredRegexp`, `PrivateMemberHungarianPrefix`
> - `PrivateMethodCase`, `PrivateMethodPrefix`, `PrivateMethodSuffix`,
>   `PrivateMethodIgnoredRegexp`
> - `ProtectedMemberCase`, `ProtectedMemberPrefix`,
>   `ProtectedMemberSuffix`, `ProtectedMemberIgnoredRegexp`,
>   `ProtectedMemberHungarianPrefix`
> - `ProtectedMethodCase`, `ProtectedMethodPrefix`,
>   `ProtectedMethodSuffix`, `ProtectedMethodIgnoredRegexp`
> - `PublicMemberCase`, `PublicMemberPrefix`, `PublicMemberSuffix`,
>   `PublicMemberIgnoredRegexp`, `PublicMemberHungarianPrefix`
> - `PublicMethodCase`, `PublicMethodPrefix`, `PublicMethodSuffix`,
>   `PublicMethodIgnoredRegexp`
> - `ScopedEnumConstantCase`, `ScopedEnumConstantPrefix`,
>   `ScopedEnumConstantSuffix`, `ScopedEnumConstantIgnoredRegexp`
> - `StaticConstantCase`, `StaticConstantPrefix`,
>   `StaticConstantSuffix`, `StaticConstantIgnoredRegexp`,
>   `StaticConstantHungarianPrefix`
> - `StaticVariableCase`, `StaticVariablePrefix`,
>   `StaticVariableSuffix`, `StaticVariableIgnoredRegexp`,
>   `StaticVariableHungarianPrefix`
> - `StructCase`, `StructPrefix`, `StructSuffix`, `StructIgnoredRegexp`
> - `TemplateParameterCase`, `TemplateParameterPrefix`,
>   `TemplateParameterSuffix`, `TemplateParameterIgnoredRegexp`
> - `TemplateTemplateParameterCase`, `TemplateTemplateParameterPrefix`,
>   `TemplateTemplateParameterSuffix`,
>   `TemplateTemplateParameterIgnoredRegexp`
> - `TypeAliasCase`, `TypeAliasPrefix`, `TypeAliasSuffix`,
>   `TypeAliasIgnoredRegexp`
> - `TypedefCase`, `TypedefPrefix`, `TypedefSuffix`,
>   `TypedefIgnoredRegexp`
> - `TypeTemplateParameterCase`, `TypeTemplateParameterPrefix`,
>   `TypeTemplateParameterSuffix`, `TypeTemplateParameterIgnoredRegexp`
> - `UnionCase`, `UnionPrefix`, `UnionSuffix`, `UnionIgnoredRegexp`
> - `ValueTemplateParameterCase`, `ValueTemplateParameterPrefix`,
>   `ValueTemplateParameterSuffix`,
>   `ValueTemplateParameterIgnoredRegexp`
> - `VariableCase`, `VariablePrefix`, `VariableSuffix`,
>   `VariableIgnoredRegexp`, `VariableHungarianPrefix`
> - `VirtualMethodCase`, `VirtualMethodPrefix`, `VirtualMethodSuffix`,
>   `VirtualMethodIgnoredRegexp`

<div class="option">

AbstractClassCase

When defined, the check will ensure abstract class names conform to the
selected casing.

</div>

<div class="option">

AbstractClassPrefix

When defined, the check will ensure abstract class names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

AbstractClassIgnoredRegexp

Identifier naming checks won't be enforced for abstract class names
matching this regular expression.

</div>

<div class="option">

AbstractClassSuffix

When defined, the check will ensure abstract class names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

AbstractClassHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - AbstractClassCase of `lower_case`
> - AbstractClassPrefix of `pre_`
> - AbstractClassSuffix of `_post`
> - AbstractClassHungarianPrefix of `On`

Identifies and/or transforms abstract class names as follows:

Before:

``` c++
class ABSTRACT_CLASS {
public:
  ABSTRACT_CLASS();
};
```

After:

``` c++
class pre_abstract_class_post {
public:
  pre_abstract_class_post();
};
```

<div class="option">

AggressiveDependentMemberLookup

When set to <span class="title-ref">true</span> the check will look in
dependent base classes for dependent member references that need
changing. This can lead to errors with template specializations so the
default value is <span class="title-ref">false</span>.

</div>

For example using values of:

> - ClassMemberCase of `lower_case`

Before:

``` c++
template <typename T>
struct Base {
  T BadNamedMember;
};

template <typename T>
struct Derived : Base<T> {
  void reset() {
    this->BadNamedMember = 0;
  }
};
```

After if AggressiveDependentMemberLookup is \`false\`:

``` c++
template <typename T>
struct Base {
  T bad_named_member;
};

template <typename T>
struct Derived : Base<T> {
  void reset() {
    this->BadNamedMember = 0;
  }
};
```

After if AggressiveDependentMemberLookup is \`true\`:

``` c++
template <typename T>
struct Base {
  T bad_named_member;
};

template <typename T>
struct Derived : Base<T> {
  void reset() {
    this->bad_named_member = 0;
  }
};
```

<div class="option">

CheckAnonFieldInParent

When set to <span class="title-ref">true</span>, fields in anonymous
records (i.e. anonymous unions and structs) will be treated as names in
the enclosing scope rather than public members of the anonymous record
for the purpose of name checking.

</div>

For example:

``` c++
class Foo {
private:
  union {
    int iv_;
    float fv_;
  };
};
```

If `CheckAnonFieldInParent` is <span class="title-ref">false</span>, you
may get warnings that `iv_` and `fv_` are not coherent to public member
names, because `iv_` and `fv_` are public members of the anonymous
union. When `CheckAnonFieldInParent` is
<span class="title-ref">true</span>, `iv_` and `fv_` will be treated as
private data members of `Foo` for the purpose of name checking and thus
no warnings will be emitted.

<div class="option">

ClassCase

When defined, the check will ensure class names conform to the selected
casing.

</div>

<div class="option">

ClassPrefix

When defined, the check will ensure class names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

ClassIgnoredRegexp

Identifier naming checks won't be enforced for class names matching this
regular expression.

</div>

<div class="option">

ClassSuffix

When defined, the check will ensure class names will add the suffix with
the given value (regardless of casing).

</div>

<div class="option">

ClassHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ClassCase of `lower_case`
> - ClassPrefix of `pre_`
> - ClassSuffix of `_post`
> - ClassHungarianPrefix of `On`

Identifies and/or transforms class names as follows:

Before:

``` c++
class FOO {
public:
  FOO();
  ~FOO();
};
```

After:

``` c++
class pre_foo_post {
public:
  pre_foo_post();
  ~pre_foo_post();
};
```

<div class="option">

ClassConstantCase

When defined, the check will ensure class constant names conform to the
selected casing.

</div>

<div class="option">

ClassConstantPrefix

When defined, the check will ensure class constant names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ClassConstantIgnoredRegexp

Identifier naming checks won't be enforced for class constant names
matching this regular expression.

</div>

<div class="option">

ClassConstantSuffix

When defined, the check will ensure class constant names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

ClassConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ClassConstantCase of `lower_case`
> - ClassConstantPrefix of `pre_`
> - ClassConstantSuffix of `_post`
> - ClassConstantHungarianPrefix of `On`

Identifies and/or transforms class constant names as follows:

Before:

``` c++
class FOO {
public:
  static const int CLASS_CONSTANT;
};
```

After:

``` c++
class FOO {
public:
  static const int pre_class_constant_post;
};
```

<div class="option">

ClassMemberCase

When defined, the check will ensure class member names conform to the
selected casing.

</div>

<div class="option">

ClassMemberPrefix

When defined, the check will ensure class member names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ClassMemberIgnoredRegexp

Identifier naming checks won't be enforced for class member names
matching this regular expression.

</div>

<div class="option">

ClassMemberSuffix

When defined, the check will ensure class member names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

ClassMemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ClassMemberCase of `lower_case`
> - ClassMemberPrefix of `pre_`
> - ClassMemberSuffix of `_post`
> - ClassMemberHungarianPrefix of `On`

Identifies and/or transforms class member names as follows:

Before:

``` c++
class FOO {
public:
  static int CLASS_CONSTANT;
};
```

After:

``` c++
class FOO {
public:
  static int pre_class_constant_post;
};
```

<div class="option">

ClassMethodCase

When defined, the check will ensure class method names conform to the
selected casing.

</div>

<div class="option">

ClassMethodPrefix

When defined, the check will ensure class method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ClassMethodIgnoredRegexp

Identifier naming checks won't be enforced for class method names
matching this regular expression.

</div>

<div class="option">

ClassMethodSuffix

When defined, the check will ensure class method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ClassMethodCase of `lower_case`
> - ClassMethodPrefix of `pre_`
> - ClassMethodSuffix of `_post`

Identifies and/or transforms class method names as follows:

Before:

``` c++
class FOO {
public:
  int CLASS_MEMBER();
};
```

After:

``` c++
class FOO {
public:
  int pre_class_member_post();
};
```

<div class="option">

ConceptCase

When defined, the check will ensure concept names conform to the
selected casing.

</div>

<div class="option">

ConceptPrefix

When defined, the check will ensure concept names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

ConceptIgnoredRegexp

Identifier naming checks won't be enforced for concept names matching
this regular expression.

</div>

<div class="option">

ConceptSuffix

When defined, the check will ensure concept names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - ConceptCase of `CamelCase`
> - ConceptPrefix of `Pre`
> - ConceptSuffix of `Post`

Identifies and/or transforms concept names as follows:

Before:

``` c++
template<typename T> concept my_concept = requires (T t) { {t++}; };
```

After:

``` c++
template<typename T> concept PreMyConceptPost = requires (T t) { {t++}; };
```

<div class="option">

ConstantCase

When defined, the check will ensure constant names conform to the
selected casing.

</div>

<div class="option">

ConstantPrefix

When defined, the check will ensure constant names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

ConstantIgnoredRegexp

Identifier naming checks won't be enforced for constant names matching
this regular expression.

</div>

<div class="option">

ConstantSuffix

When defined, the check will ensure constant names will add the suffix
with the given value (regardless of casing).

</div>

<div class="option">

ConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ConstantCase of `lower_case`
> - ConstantPrefix of `pre_`
> - ConstantSuffix of `_post`
> - ConstantHungarianPrefix of `On`

Identifies and/or transforms constant names as follows:

Before:

``` c++
void function() { unsigned const MyConst_array[] = {1, 2, 3}; }
```

After:

``` c++
void function() { unsigned const pre_myconst_array_post[] = {1, 2, 3}; }
```

<div class="option">

ConstantMemberCase

When defined, the check will ensure constant member names conform to the
selected casing.

</div>

<div class="option">

ConstantMemberPrefix

When defined, the check will ensure constant member names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstantMemberIgnoredRegexp

Identifier naming checks won't be enforced for constant member names
matching this regular expression.

</div>

<div class="option">

ConstantMemberSuffix

When defined, the check will ensure constant member names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

ConstantMemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ConstantMemberCase of `lower_case`
> - ConstantMemberPrefix of `pre_`
> - ConstantMemberSuffix of `_post`
> - ConstantMemberHungarianPrefix of `On`

Identifies and/or transforms constant member names as follows:

Before:

``` c++
class Foo {
  char const MY_ConstMember_string[4] = "123";
}
```

After:

``` c++
class Foo {
  char const pre_my_constmember_string_post[4] = "123";
}
```

<div class="option">

ConstantParameterCase

When defined, the check will ensure constant parameter names conform to
the selected casing.

</div>

<div class="option">

ConstantParameterPrefix

When defined, the check will ensure constant parameter names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstantParameterIgnoredRegexp

Identifier naming checks won't be enforced for constant parameter names
matching this regular expression.

</div>

<div class="option">

ConstantParameterSuffix

When defined, the check will ensure constant parameter names will add
the suffix with the given value (regardless of casing).

</div>

<div class="option">

ConstantParameterHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ConstantParameterCase of `lower_case`
> - ConstantParameterPrefix of `pre_`
> - ConstantParameterSuffix of `_post`
> - ConstantParameterHungarianPrefix of `On`

Identifies and/or transforms constant parameter names as follows:

Before:

``` c++
void GLOBAL_FUNCTION(int PARAMETER_1, int const CONST_parameter);
```

After:

``` c++
void GLOBAL_FUNCTION(int PARAMETER_1, int const pre_const_parameter_post);
```

<div class="option">

ConstantPointerParameterCase

When defined, the check will ensure constant pointer parameter names
conform to the selected casing.

</div>

<div class="option">

ConstantPointerParameterPrefix

When defined, the check will ensure constant pointer parameter names
will add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstantPointerParameterIgnoredRegexp

Identifier naming checks won't be enforced for constant pointer
parameter names matching this regular expression.

</div>

<div class="option">

ConstantPointerParameterSuffix

When defined, the check will ensure constant pointer parameter names
will add the suffix with the given value (regardless of casing).

</div>

<div class="option">

ConstantPointerParameterHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ConstantPointerParameterCase of `lower_case`
> - ConstantPointerParameterPrefix of `pre_`
> - ConstantPointerParameterSuffix of `_post`
> - ConstantPointerParameterHungarianPrefix of `On`

Identifies and/or transforms constant pointer parameter names as
follows:

Before:

``` c++
void GLOBAL_FUNCTION(int const *CONST_parameter);
```

After:

``` c++
void GLOBAL_FUNCTION(int const *pre_const_parameter_post);
```

<div class="option">

ConstexprFunctionCase

When defined, the check will ensure constexpr function names conform to
the selected casing.

</div>

<div class="option">

ConstexprFunctionPrefix

When defined, the check will ensure constexpr function names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstexprFunctionIgnoredRegexp

Identifier naming checks won't be enforced for constexpr function names
matching this regular expression.

</div>

<div class="option">

ConstexprFunctionSuffix

When defined, the check will ensure constexpr function names will add
the suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ConstexprFunctionCase of `lower_case`
> - ConstexprFunctionPrefix of `pre_`
> - ConstexprFunctionSuffix of `_post`

Identifies and/or transforms constexpr function names as follows:

Before:

``` c++
constexpr int CE_function() { return 3; }
```

After:

``` c++
constexpr int pre_ce_function_post() { return 3; }
```

<div class="option">

ConstexprMethodCase

When defined, the check will ensure constexpr method names conform to
the selected casing.

</div>

<div class="option">

ConstexprMethodPrefix

When defined, the check will ensure constexpr method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstexprMethodIgnoredRegexp

Identifier naming checks won't be enforced for constexpr method names
matching this regular expression.

</div>

<div class="option">

ConstexprMethodSuffix

When defined, the check will ensure constexpr method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ConstexprMethodCase of `lower_case`
> - ConstexprMethodPrefix of `pre_`
> - ConstexprMethodSuffix of `_post`

Identifies and/or transforms constexpr method names as follows:

Before:

``` c++
class Foo {
public:
  constexpr int CST_expr_Method() { return 2; }
}
```

After:

``` c++
class Foo {
public:
  constexpr int pre_cst_expr_method_post() { return 2; }
}
```

<div class="option">

ConstexprVariableCase

When defined, the check will ensure constexpr variable names conform to
the selected casing.

</div>

<div class="option">

ConstexprVariablePrefix

When defined, the check will ensure constexpr variable names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ConstexprVariableIgnoredRegexp

Identifier naming checks won't be enforced for constexpr variable names
matching this regular expression.

</div>

<div class="option">

ConstexprVariableSuffix

When defined, the check will ensure constexpr variable names will add
the suffix with the given value (regardless of casing).

</div>

<div class="option">

ConstexprVariableHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ConstexprVariableCase of `lower_case`
> - ConstexprVariablePrefix of `pre_`
> - ConstexprVariableSuffix of `_post`
> - ConstexprVariableHungarianPrefix of `On`

Identifies and/or transforms constexpr variable names as follows:

Before:

``` c++
constexpr int ConstExpr_variable = MyConstant;
```

After:

``` c++
constexpr int pre_constexpr_variable_post = MyConstant;
```

<div class="option">

EnumCase

When defined, the check will ensure enumeration names conform to the
selected casing.

</div>

<div class="option">

EnumPrefix

When defined, the check will ensure enumeration names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

EnumIgnoredRegexp

Identifier naming checks won't be enforced for enumeration names
matching this regular expression.

</div>

<div class="option">

EnumSuffix

When defined, the check will ensure enumeration names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - EnumCase of `lower_case`
> - EnumPrefix of `pre_`
> - EnumSuffix of `_post`

Identifies and/or transforms enumeration names as follows:

Before:

``` c++
enum FOO { One, Two, Three };
```

After:

``` c++
enum pre_foo_post { One, Two, Three };
```

<div class="option">

EnumConstantCase

When defined, the check will ensure enumeration constant names conform
to the selected casing.

</div>

<div class="option">

EnumConstantPrefix

When defined, the check will ensure enumeration constant names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

EnumConstantIgnoredRegexp

Identifier naming checks won't be enforced for enumeration constant
names matching this regular expression.

</div>

<div class="option">

EnumConstantSuffix

When defined, the check will ensure enumeration constant names will add
the suffix with the given value (regardless of casing).

</div>

<div class="option">

EnumConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - EnumConstantCase of `lower_case`
> - EnumConstantPrefix of `pre_`
> - EnumConstantSuffix of `_post`
> - EnumConstantHungarianPrefix of `On`

Identifies and/or transforms enumeration constant names as follows:

Before:

``` c++
enum FOO { One, Two, Three };
```

After:

``` c++
enum FOO { pre_One_post, pre_Two_post, pre_Three_post };
```

<div class="option">

FunctionCase

When defined, the check will ensure function names conform to the
selected casing.

</div>

<div class="option">

FunctionPrefix

When defined, the check will ensure function names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

FunctionIgnoredRegexp

Identifier naming checks won't be enforced for function names matching
this regular expression.

</div>

<div class="option">

FunctionSuffix

When defined, the check will ensure function names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - FunctionCase of `lower_case`
> - FunctionPrefix of `pre_`
> - FunctionSuffix of `_post`

Identifies and/or transforms function names as follows:

Before:

``` c++
char MY_Function_string();
```

After:

``` c++
char pre_my_function_string_post();
```

<div class="option">

GetConfigPerFile

When <span class="title-ref">true</span> the check will look for the
configuration for where an identifier is declared. Useful for when
included header files use a different style. Default value is
<span class="title-ref">true</span>.

</div>

<div class="option">

GlobalConstantCase

When defined, the check will ensure global constant names conform to the
selected casing.

</div>

<div class="option">

GlobalConstantPrefix

When defined, the check will ensure global constant names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

GlobalConstantIgnoredRegexp

Identifier naming checks won't be enforced for global constant names
matching this regular expression.

</div>

<div class="option">

GlobalConstantSuffix

When defined, the check will ensure global constant names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

GlobalConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - GlobalConstantCase of `lower_case`
> - GlobalConstantPrefix of `pre_`
> - GlobalConstantSuffix of `_post`
> - GlobalConstantHungarianPrefix of `On`

Identifies and/or transforms global constant names as follows:

Before:

``` c++
unsigned const MyConstGlobal_array[] = {1, 2, 3};
```

After:

``` c++
unsigned const pre_myconstglobal_array_post[] = {1, 2, 3};
```

<div class="option">

GlobalConstantPointerCase

When defined, the check will ensure global constant pointer names
conform to the selected casing.

</div>

<div class="option">

GlobalConstantPointerPrefix

When defined, the check will ensure global constant pointer names will
add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

GlobalConstantPointerIgnoredRegexp

Identifier naming checks won't be enforced for global constant pointer
names matching this regular expression.

</div>

<div class="option">

GlobalConstantPointerSuffix

When defined, the check will ensure global constant pointer names will
add the suffix with the given value (regardless of casing).

</div>

<div class="option">

GlobalConstantPointerHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - GlobalConstantPointerCase of `lower_case`
> - GlobalConstantPointerPrefix of `pre_`
> - GlobalConstantPointerSuffix of `_post`
> - GlobalConstantPointerHungarianPrefix of `On`

Identifies and/or transforms global constant pointer names as follows:

Before:

``` c++
int *const MyConstantGlobalPointer = nullptr;
```

After:

``` c++
int *const pre_myconstantglobalpointer_post = nullptr;
```

<div class="option">

GlobalFunctionCase

When defined, the check will ensure global function names conform to the
selected casing.

</div>

<div class="option">

GlobalFunctionPrefix

When defined, the check will ensure global function names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

GlobalFunctionIgnoredRegexp

Identifier naming checks won't be enforced for global function names
matching this regular expression.

</div>

<div class="option">

GlobalFunctionSuffix

When defined, the check will ensure global function names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - GlobalFunctionCase of `lower_case`
> - GlobalFunctionPrefix of `pre_`
> - GlobalFunctionSuffix of `_post`

Identifies and/or transforms global function names as follows:

Before:

``` c++
void GLOBAL_FUNCTION(int PARAMETER_1, int const CONST_parameter);
```

After:

``` c++
void pre_global_function_post(int PARAMETER_1, int const CONST_parameter);
```

<div class="option">

GlobalPointerCase

When defined, the check will ensure global pointer names conform to the
selected casing.

</div>

<div class="option">

GlobalPointerPrefix

When defined, the check will ensure global pointer names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

GlobalPointerIgnoredRegexp

Identifier naming checks won't be enforced for global pointer names
matching this regular expression.

</div>

<div class="option">

GlobalPointerSuffix

When defined, the check will ensure global pointer names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

GlobalPointerHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - GlobalPointerCase of `lower_case`
> - GlobalPointerPrefix of `pre_`
> - GlobalPointerSuffix of `_post`
> - GlobalPointerHungarianPrefix of `On`

Identifies and/or transforms global pointer names as follows:

Before:

``` c++
int *GLOBAL3;
```

After:

``` c++
int *pre_global3_post;
```

<div class="option">

GlobalVariableCase

When defined, the check will ensure global variable names conform to the
selected casing.

</div>

<div class="option">

GlobalVariablePrefix

When defined, the check will ensure global variable names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

GlobalVariableIgnoredRegexp

Identifier naming checks won't be enforced for global variable names
matching this regular expression.

</div>

<div class="option">

GlobalVariableSuffix

When defined, the check will ensure global variable names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

GlobalVariableHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - GlobalVariableCase of `lower_case`
> - GlobalVariablePrefix of `pre_`
> - GlobalVariableSuffix of `_post`
> - GlobalVariableHungarianPrefix of `On`

Identifies and/or transforms global variable names as follows:

Before:

``` c++
int GLOBAL3;
```

After:

``` c++
int pre_global3_post;
```

<div class="option">

IgnoreMainLikeFunctions

When set to <span class="title-ref">true</span> functions that have a
similar signature to `main` or `wmain` won't enforce checks on the names
of their parameters. Default value is
<span class="title-ref">false</span>.

</div>

<div class="option">

InlineNamespaceCase

When defined, the check will ensure inline namespaces names conform to
the selected casing.

</div>

<div class="option">

InlineNamespacePrefix

When defined, the check will ensure inline namespaces names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

InlineNamespaceIgnoredRegexp

Identifier naming checks won't be enforced for inline namespaces names
matching this regular expression.

</div>

<div class="option">

InlineNamespaceSuffix

When defined, the check will ensure inline namespaces names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - InlineNamespaceCase of `lower_case`
> - InlineNamespacePrefix of `pre_`
> - InlineNamespaceSuffix of `_post`

Identifies and/or transforms inline namespaces names as follows:

Before:

``` c++
namespace FOO_NS {
inline namespace InlineNamespace {
...
}
} // namespace FOO_NS
```

After:

``` c++
namespace FOO_NS {
inline namespace pre_inlinenamespace_post {
...
}
} // namespace FOO_NS
```

<div class="option">

LocalConstantCase

When defined, the check will ensure local constant names conform to the
selected casing.

</div>

<div class="option">

LocalConstantPrefix

When defined, the check will ensure local constant names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

LocalConstantIgnoredRegexp

Identifier naming checks won't be enforced for local constant names
matching this regular expression.

</div>

<div class="option">

LocalConstantSuffix

When defined, the check will ensure local constant names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

LocalConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - LocalConstantCase of `lower_case`
> - LocalConstantPrefix of `pre_`
> - LocalConstantSuffix of `_post`
> - LocalConstantHungarianPrefix of `On`

Identifies and/or transforms local constant names as follows:

Before:

``` c++
void foo() { int const local_Constant = 3; }
```

After:

``` c++
void foo() { int const pre_local_constant_post = 3; }
```

<div class="option">

LocalConstantPointerCase

When defined, the check will ensure local constant pointer names conform
to the selected casing.

</div>

<div class="option">

LocalConstantPointerPrefix

When defined, the check will ensure local constant pointer names will
add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

LocalConstantPointerIgnoredRegexp

Identifier naming checks won't be enforced for local constant pointer
names matching this regular expression.

</div>

<div class="option">

LocalConstantPointerSuffix

When defined, the check will ensure local constant pointer names will
add the suffix with the given value (regardless of casing).

</div>

<div class="option">

LocalConstantPointerHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - LocalConstantPointerCase of `lower_case`
> - LocalConstantPointerPrefix of `pre_`
> - LocalConstantPointerSuffix of `_post`
> - LocalConstantPointerHungarianPrefix of `On`

Identifies and/or transforms local constant pointer names as follows:

Before:

``` c++
void foo() { int const *local_Constant = 3; }
```

After:

``` c++
void foo() { int const *pre_local_constant_post = 3; }
```

<div class="option">

LocalPointerCase

When defined, the check will ensure local pointer names conform to the
selected casing.

</div>

<div class="option">

LocalPointerPrefix

When defined, the check will ensure local pointer names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

LocalPointerIgnoredRegexp

Identifier naming checks won't be enforced for local pointer names
matching this regular expression.

</div>

<div class="option">

LocalPointerSuffix

When defined, the check will ensure local pointer names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

LocalPointerHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - LocalPointerCase of `lower_case`
> - LocalPointerPrefix of `pre_`
> - LocalPointerSuffix of `_post`
> - LocalPointerHungarianPrefix of `On`

Identifies and/or transforms local pointer names as follows:

Before:

``` c++
void foo() { int *local_Constant; }
```

After:

``` c++
void foo() { int *pre_local_constant_post; }
```

<div class="option">

LocalVariableCase

When defined, the check will ensure local variable names conform to the
selected casing.

</div>

<div class="option">

LocalVariablePrefix

When defined, the check will ensure local variable names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

LocalVariableIgnoredRegexp

Identifier naming checks won't be enforced for local variable names
matching this regular expression.

</div>

For example using values of:

> - LocalVariableCase of `CamelCase`
> - LocalVariableIgnoredRegexp of `\w{1,2}`

Will exclude variables with a length less than or equal to 2 from the
camel case check applied to other variables.

<div class="option">

LocalVariableSuffix

When defined, the check will ensure local variable names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

LocalVariableHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - LocalVariableCase of `lower_case`
> - LocalVariablePrefix of `pre_`
> - LocalVariableSuffix of `_post`
> - LocalVariableHungarianPrefix of `On`

Identifies and/or transforms local variable names as follows:

Before:

``` c++
void foo() { int local_Constant; }
```

After:

``` c++
void foo() { int pre_local_constant_post; }
```

<div class="option">

MacroDefinitionCase

When defined, the check will ensure macro definitions conform to the
selected casing.

</div>

<div class="option">

MacroDefinitionPrefix

When defined, the check will ensure macro definitions will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

MacroDefinitionIgnoredRegexp

Identifier naming checks won't be enforced for macro definitions
matching this regular expression.

</div>

<div class="option">

MacroDefinitionSuffix

When defined, the check will ensure macro definitions will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - MacroDefinitionCase of `lower_case`
> - MacroDefinitionPrefix of `pre_`
> - MacroDefinitionSuffix of `_post`

Identifies and/or transforms macro definitions as follows:

Before:

``` c
#define MY_MacroDefinition
```

After:

``` c
#define pre_my_macro_definition_post
```

Note: This will not warn on builtin macros or macros defined on the
command line using the `-D` flag.

<div class="option">

MemberCase

When defined, the check will ensure member names conform to the selected
casing.

</div>

<div class="option">

MemberPrefix

When defined, the check will ensure member names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

MemberIgnoredRegexp

Identifier naming checks won't be enforced for member names matching
this regular expression.

</div>

<div class="option">

MemberSuffix

When defined, the check will ensure member names will add the suffix
with the given value (regardless of casing).

</div>

<div class="option">

MemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - MemberCase of `lower_case`
> - MemberPrefix of `pre_`
> - MemberSuffix of `_post`
> - MemberHungarianPrefix of `On`

Identifies and/or transforms member names as follows:

Before:

``` c++
class Foo {
  char MY_ConstMember_string[4];
}
```

After:

``` c++
class Foo {
  char pre_my_constmember_string_post[4];
}
```

<div class="option">

MethodCase

When defined, the check will ensure method names conform to the selected
casing.

</div>

<div class="option">

MethodPrefix

When defined, the check will ensure method names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

MethodIgnoredRegexp

Identifier naming checks won't be enforced for method names matching
this regular expression.

</div>

<div class="option">

MethodSuffix

When defined, the check will ensure method names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - MethodCase of `lower_case`
> - MethodPrefix of `pre_`
> - MethodSuffix of `_post`

Identifies and/or transforms method names as follows:

Before:

``` c++
class Foo {
  char MY_Method_string();
}
```

After:

``` c++
class Foo {
  char pre_my_method_string_post();
}
```

<div class="option">

NamespaceCase

When defined, the check will ensure namespace names conform to the
selected casing.

</div>

<div class="option">

NamespacePrefix

When defined, the check will ensure namespace names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

NamespaceIgnoredRegexp

Identifier naming checks won't be enforced for namespace names matching
this regular expression.

</div>

<div class="option">

NamespaceSuffix

When defined, the check will ensure namespace names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - NamespaceCase of `lower_case`
> - NamespacePrefix of `pre_`
> - NamespaceSuffix of `_post`

Identifies and/or transforms namespace names as follows:

Before:

``` c++
namespace FOO_NS {
...
}
```

After:

``` c++
namespace pre_foo_ns_post {
...
}
```

<div class="option">

ParameterCase

When defined, the check will ensure parameter names conform to the
selected casing.

</div>

<div class="option">

ParameterPrefix

When defined, the check will ensure parameter names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ParameterIgnoredRegexp

Identifier naming checks won't be enforced for parameter names matching
this regular expression.

</div>

<div class="option">

ParameterSuffix

When defined, the check will ensure parameter names will add the suffix
with the given value (regardless of casing).

</div>

<div class="option">

ParameterHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ParameterCase of `lower_case`
> - ParameterPrefix of `pre_`
> - ParameterSuffix of `_post`
> - ParameterHungarianPrefix of `On`

Identifies and/or transforms parameter names as follows:

Before:

``` c++
void GLOBAL_FUNCTION(int PARAMETER_1, int const CONST_parameter);
```

After:

``` c++
void GLOBAL_FUNCTION(int pre_parameter_post, int const CONST_parameter);
```

<div class="option">

ParameterPackCase

When defined, the check will ensure parameter pack names conform to the
selected casing.

</div>

<div class="option">

ParameterPackPrefix

When defined, the check will ensure parameter pack names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ParameterPackIgnoredRegexp

Identifier naming checks won't be enforced for parameter pack names
matching this regular expression.

</div>

<div class="option">

ParameterPackSuffix

When defined, the check will ensure parameter pack names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ParameterPackCase of `lower_case`
> - ParameterPackPrefix of `pre_`
> - ParameterPackSuffix of `_post`

Identifies and/or transforms parameter pack names as follows:

Before:

``` c++
template <typename... TYPE_parameters> {
  void FUNCTION(int... TYPE_parameters);
}
```

After:

``` c++
template <typename... TYPE_parameters> {
  void FUNCTION(int... pre_type_parameters_post);
}
```

<div class="option">

PointerParameterCase

When defined, the check will ensure pointer parameter names conform to
the selected casing.

</div>

<div class="option">

PointerParameterPrefix

When defined, the check will ensure pointer parameter names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

PointerParameterIgnoredRegexp

Identifier naming checks won't be enforced for pointer parameter names
matching this regular expression.

</div>

<div class="option">

PointerParameterSuffix

When defined, the check will ensure pointer parameter names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

PointerParameterHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - PointerParameterCase of `lower_case`
> - PointerParameterPrefix of `pre_`
> - PointerParameterSuffix of `_post`
> - PointerParameterHungarianPrefix of `On`

Identifies and/or transforms pointer parameter names as follows:

Before:

``` c++
void FUNCTION(int *PARAMETER);
```

After:

``` c++
void FUNCTION(int *pre_parameter_post);
```

<div class="option">

PrivateMemberCase

When defined, the check will ensure private member names conform to the
selected casing.

</div>

<div class="option">

PrivateMemberPrefix

When defined, the check will ensure private member names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

PrivateMemberIgnoredRegexp

Identifier naming checks won't be enforced for private member names
matching this regular expression.

</div>

<div class="option">

PrivateMemberSuffix

When defined, the check will ensure private member names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

PrivateMemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - PrivateMemberCase of `lower_case`
> - PrivateMemberPrefix of `pre_`
> - PrivateMemberSuffix of `_post`
> - PrivateMemberHungarianPrefix of `On`

Identifies and/or transforms private member names as follows:

Before:

``` c++
class Foo {
private:
  int Member_Variable;
}
```

After:

``` c++
class Foo {
private:
  int pre_member_variable_post;
}
```

<div class="option">

PrivateMethodCase

When defined, the check will ensure private method names conform to the
selected casing.

</div>

<div class="option">

PrivateMethodPrefix

When defined, the check will ensure private method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

PrivateMethodIgnoredRegexp

Identifier naming checks won't be enforced for private method names
matching this regular expression.

</div>

<div class="option">

PrivateMethodSuffix

When defined, the check will ensure private method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - PrivateMethodCase of `lower_case`
> - PrivateMethodPrefix of `pre_`
> - PrivateMethodSuffix of `_post`

Identifies and/or transforms private method names as follows:

Before:

``` c++
class Foo {
private:
  int Member_Method();
}
```

After:

``` c++
class Foo {
private:
  int pre_member_method_post();
}
```

<div class="option">

ProtectedMemberCase

When defined, the check will ensure protected member names conform to
the selected casing.

</div>

<div class="option">

ProtectedMemberPrefix

When defined, the check will ensure protected member names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ProtectedMemberIgnoredRegexp

Identifier naming checks won't be enforced for protected member names
matching this regular expression.

</div>

<div class="option">

ProtectedMemberSuffix

When defined, the check will ensure protected member names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

ProtectedMemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ProtectedMemberCase of `lower_case`
> - ProtectedMemberPrefix of `pre_`
> - ProtectedMemberSuffix of `_post`
> - ProtectedMemberHungarianPrefix of `On`

Identifies and/or transforms protected member names as follows:

Before:

``` c++
class Foo {
protected:
  int Member_Variable;
}
```

After:

``` c++
class Foo {
protected:
  int pre_member_variable_post;
}
```

<div class="option">

ProtectedMethodCase

When defined, the check will ensure protected method names conform to
the selected casing.

</div>

<div class="option">

ProtectedMethodPrefix

When defined, the check will ensure protected method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

ProtectedMethodIgnoredRegexp

Identifier naming checks won't be enforced for protected method names
matching this regular expression.

</div>

<div class="option">

ProtectedMethodSuffix

When defined, the check will ensure protected method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ProtectedMethodCase of `lower_case`
> - ProtectedMethodPrefix of `pre_`
> - ProtectedMethodSuffix of `_post`

Identifies and/or transforms protect method names as follows:

Before:

``` c++
class Foo {
protected:
  int Member_Method();
}
```

After:

``` c++
class Foo {
protected:
  int pre_member_method_post();
}
```

<div class="option">

PublicMemberCase

When defined, the check will ensure public member names conform to the
selected casing.

</div>

<div class="option">

PublicMemberPrefix

When defined, the check will ensure public member names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

PublicMemberIgnoredRegexp

Identifier naming checks won't be enforced for public member names
matching this regular expression.

</div>

<div class="option">

PublicMemberSuffix

When defined, the check will ensure public member names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

PublicMemberHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - PublicMemberCase of `lower_case`
> - PublicMemberPrefix of `pre_`
> - PublicMemberSuffix of `_post`
> - PublicMemberHungarianPrefix of `On`

Identifies and/or transforms public member names as follows:

Before:

``` c++
class Foo {
public:
  int Member_Variable;
}
```

After:

``` c++
class Foo {
public:
  int pre_member_variable_post;
}
```

<div class="option">

PublicMethodCase

When defined, the check will ensure public method names conform to the
selected casing.

</div>

<div class="option">

PublicMethodPrefix

When defined, the check will ensure public method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

PublicMethodIgnoredRegexp

Identifier naming checks won't be enforced for public method names
matching this regular expression.

</div>

<div class="option">

PublicMethodSuffix

When defined, the check will ensure public method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - PublicMethodCase of `lower_case`
> - PublicMethodPrefix of `pre_`
> - PublicMethodSuffix of `_post`

Identifies and/or transforms public method names as follows:

Before:

``` c++
class Foo {
public:
  int Member_Method();
}
```

After:

``` c++
class Foo {
public:
  int pre_member_method_post();
}
```

<div class="option">

ScopedEnumConstantCase

When defined, the check will ensure scoped enum constant names conform
to the selected casing.

</div>

<div class="option">

ScopedEnumConstantPrefix

When defined, the check will ensure scoped enum constant names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ScopedEnumConstantIgnoredRegexp

Identifier naming checks won't be enforced for scoped enum constant
names matching this regular expression.

</div>

<div class="option">

ScopedEnumConstantSuffix

When defined, the check will ensure scoped enum constant names will add
the suffix with the given value (regardless of casing).

</div>

<div class="option">

ScopedEnumConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - ScopedEnumConstantCase of `lower_case`
> - ScopedEnumConstantPrefix of `pre_`
> - ScopedEnumConstantSuffix of `_post`
> - ScopedEnumConstantHungarianPrefix of `On`

Identifies and/or transforms enumeration constant names as follows:

Before:

``` c++
enum class FOO { One, Two, Three };
```

After:

``` c++
enum class FOO { pre_One_post, pre_Two_post, pre_Three_post };
```

<div class="option">

StaticConstantCase

When defined, the check will ensure static constant names conform to the
selected casing.

</div>

<div class="option">

StaticConstantPrefix

When defined, the check will ensure static constant names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

StaticConstantIgnoredRegexp

Identifier naming checks won't be enforced for static constant names
matching this regular expression.

</div>

<div class="option">

StaticConstantSuffix

When defined, the check will ensure static constant names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

StaticConstantHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - StaticConstantCase of `lower_case`
> - StaticConstantPrefix of `pre_`
> - StaticConstantSuffix of `_post`
> - StaticConstantHungarianPrefix of `On`

Identifies and/or transforms static constant names as follows:

Before:

``` c++
static unsigned const MyConstStatic_array[] = {1, 2, 3};
```

After:

``` c++
static unsigned const pre_myconststatic_array_post[] = {1, 2, 3};
```

<div class="option">

StaticVariableCase

When defined, the check will ensure static variable names conform to the
selected casing.

</div>

<div class="option">

StaticVariablePrefix

When defined, the check will ensure static variable names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

StaticVariableIgnoredRegexp

Identifier naming checks won't be enforced for static variable names
matching this regular expression.

</div>

<div class="option">

StaticVariableSuffix

When defined, the check will ensure static variable names will add the
suffix with the given value (regardless of casing).

</div>

<div class="option">

StaticVariableHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - StaticVariableCase of `lower_case`
> - StaticVariablePrefix of `pre_`
> - StaticVariableSuffix of `_post`
> - StaticVariableHungarianPrefix of `On`

Identifies and/or transforms static variable names as follows:

Before:

``` c++
static unsigned MyStatic_array[] = {1, 2, 3};
```

After:

``` c++
static unsigned pre_mystatic_array_post[] = {1, 2, 3};
```

<div class="option">

StructCase

When defined, the check will ensure struct names conform to the selected
casing.

</div>

<div class="option">

StructPrefix

When defined, the check will ensure struct names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

StructIgnoredRegexp

Identifier naming checks won't be enforced for struct names matching
this regular expression.

</div>

<div class="option">

StructSuffix

When defined, the check will ensure struct names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - StructCase of `lower_case`
> - StructPrefix of `pre_`
> - StructSuffix of `_post`

Identifies and/or transforms struct names as follows:

Before:

``` c++
struct FOO {
  FOO();
  ~FOO();
};
```

After:

``` c++
struct pre_foo_post {
  pre_foo_post();
  ~pre_foo_post();
};
```

<div class="option">

TemplateParameterCase

When defined, the check will ensure template parameter names conform to
the selected casing.

</div>

<div class="option">

TemplateParameterPrefix

When defined, the check will ensure template parameter names will add
the prefixed with the given value (regardless of casing).

</div>

<div class="option">

TemplateParameterIgnoredRegexp

Identifier naming checks won't be enforced for template parameter names
matching this regular expression.

</div>

<div class="option">

TemplateParameterSuffix

When defined, the check will ensure template parameter names will add
the suffix with the given value (regardless of casing).

</div>

For example using values of:

> - TemplateParameterCase of `lower_case`
> - TemplateParameterPrefix of `pre_`
> - TemplateParameterSuffix of `_post`

Identifies and/or transforms template parameter names as follows:

Before:

``` c++
template <typename T> class Foo {};
```

After:

``` c++
template <typename pre_t_post> class Foo {};
```

<div class="option">

TemplateTemplateParameterCase

When defined, the check will ensure template template parameter names
conform to the selected casing.

</div>

<div class="option">

TemplateTemplateParameterPrefix

When defined, the check will ensure template template parameter names
will add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

TemplateTemplateParameterIgnoredRegexp

Identifier naming checks won't be enforced for template template
parameter names matching this regular expression.

</div>

<div class="option">

TemplateTemplateParameterSuffix

When defined, the check will ensure template template parameter names
will add the suffix with the given value (regardless of casing).

</div>

For example using values of:

> - TemplateTemplateParameterCase of `lower_case`
> - TemplateTemplateParameterPrefix of `pre_`
> - TemplateTemplateParameterSuffix of `_post`

Identifies and/or transforms template template parameter names as
follows:

Before:

``` c++
template <template <typename> class TPL_parameter, int COUNT_params,
          typename... TYPE_parameters>
```

After:

``` c++
template <template <typename> class pre_tpl_parameter_post, int COUNT_params,
          typename... TYPE_parameters>
```

<div class="option">

TypeAliasCase

When defined, the check will ensure type alias names conform to the
selected casing.

</div>

<div class="option">

TypeAliasPrefix

When defined, the check will ensure type alias names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

TypeAliasIgnoredRegexp

Identifier naming checks won't be enforced for type alias names matching
this regular expression.

</div>

<div class="option">

TypeAliasSuffix

When defined, the check will ensure type alias names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - TypeAliasCase of `lower_case`
> - TypeAliasPrefix of `pre_`
> - TypeAliasSuffix of `_post`

Identifies and/or transforms type alias names as follows:

Before:

``` c++
using MY_STRUCT_TYPE = my_structure;
```

After:

``` c++
using pre_my_struct_type_post = my_structure;
```

<div class="option">

TypedefCase

When defined, the check will ensure typedef names conform to the
selected casing.

</div>

<div class="option">

TypedefPrefix

When defined, the check will ensure typedef names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

TypedefIgnoredRegexp

Identifier naming checks won't be enforced for typedef names matching
this regular expression.

</div>

<div class="option">

TypedefSuffix

When defined, the check will ensure typedef names will add the suffix
with the given value (regardless of casing).

</div>

For example using values of:

> - TypedefCase of `lower_case`
> - TypedefPrefix of `pre_`
> - TypedefSuffix of `_post`

Identifies and/or transforms typedef names as follows:

Before:

``` c++
typedef int MYINT;
```

After:

``` c++
typedef int pre_myint_post;
```

<div class="option">

TypeTemplateParameterCase

When defined, the check will ensure type template parameter names
conform to the selected casing.

</div>

<div class="option">

TypeTemplateParameterPrefix

When defined, the check will ensure type template parameter names will
add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

TypeTemplateParameterIgnoredRegexp

Identifier naming checks won't be enforced for type template names
matching this regular expression.

</div>

<div class="option">

TypeTemplateParameterSuffix

When defined, the check will ensure type template parameter names will
add the suffix with the given value (regardless of casing).

</div>

For example using values of:

> - TypeTemplateParameterCase of `lower_case`
> - TypeTemplateParameterPrefix of `pre_`
> - TypeTemplateParameterSuffix of `_post`

Identifies and/or transforms type template parameter names as follows:

Before:

``` c++
template <template <typename> class TPL_parameter, int COUNT_params,
          typename... TYPE_parameters>
```

After:

``` c++
template <template <typename> class TPL_parameter, int COUNT_params,
          typename... pre_type_parameters_post>
```

<div class="option">

UnionCase

When defined, the check will ensure union names conform to the selected
casing.

</div>

<div class="option">

UnionPrefix

When defined, the check will ensure union names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

UnionIgnoredRegexp

Identifier naming checks won't be enforced for union names matching this
regular expression.

</div>

<div class="option">

UnionSuffix

When defined, the check will ensure union names will add the suffix with
the given value (regardless of casing).

</div>

For example using values of:

> - UnionCase of `lower_case`
> - UnionPrefix of `pre_`
> - UnionSuffix of `_post`

Identifies and/or transforms union names as follows:

Before:

``` c++
union FOO {
  int a;
  char b;
};
```

After:

``` c++
union pre_foo_post {
  int a;
  char b;
};
```

<div class="option">

ValueTemplateParameterCase

When defined, the check will ensure value template parameter names
conform to the selected casing.

</div>

<div class="option">

ValueTemplateParameterPrefix

When defined, the check will ensure value template parameter names will
add the prefixed with the given value (regardless of casing).

</div>

<div class="option">

ValueTemplateParameterIgnoredRegexp

Identifier naming checks won't be enforced for value template parameter
names matching this regular expression.

</div>

<div class="option">

ValueTemplateParameterSuffix

When defined, the check will ensure value template parameter names will
add the suffix with the given value (regardless of casing).

</div>

For example using values of:

> - ValueTemplateParameterCase of `lower_case`
> - ValueTemplateParameterPrefix of `pre_`
> - ValueTemplateParameterSuffix of `_post`

Identifies and/or transforms value template parameter names as follows:

Before:

``` c++
template <template <typename> class TPL_parameter, int COUNT_params,
          typename... TYPE_parameters>
```

After:

``` c++
template <template <typename> class TPL_parameter, int pre_count_params_post,
          typename... TYPE_parameters>
```

<div class="option">

VariableCase

When defined, the check will ensure variable names conform to the
selected casing.

</div>

<div class="option">

VariablePrefix

When defined, the check will ensure variable names will add the prefixed
with the given value (regardless of casing).

</div>

<div class="option">

VariableIgnoredRegexp

Identifier naming checks won't be enforced for variable names matching
this regular expression.

</div>

<div class="option">

VariableSuffix

When defined, the check will ensure variable names will add the suffix
with the given value (regardless of casing).

</div>

<div class="option">

VariableHungarianPrefix

When enabled, the check ensures that the declared identifier will have a
Hungarian notation prefix based on the declared type.

</div>

For example using values of:

> - VariableCase of `lower_case`
> - VariablePrefix of `pre_`
> - VariableSuffix of `_post`
> - VariableHungarianPrefix of `On`

Identifies and/or transforms variable names as follows:

Before:

``` c++
unsigned MyVariable;
```

After:

``` c++
unsigned pre_myvariable_post;
```

<div class="option">

VirtualMethodCase

When defined, the check will ensure virtual method names conform to the
selected casing.

</div>

<div class="option">

VirtualMethodPrefix

When defined, the check will ensure virtual method names will add the
prefixed with the given value (regardless of casing).

</div>

<div class="option">

VirtualMethodIgnoredRegexp

Identifier naming checks won't be enforced for virtual method names
matching this regular expression.

</div>

<div class="option">

VirtualMethodSuffix

When defined, the check will ensure virtual method names will add the
suffix with the given value (regardless of casing).

</div>

For example using values of:

> - VirtualMethodCase of `lower_case`
> - VirtualMethodPrefix of `pre_`
> - VirtualMethodSuffix of `_post`

Identifies and/or transforms virtual method names as follows:

Before:

``` c++
class Foo {
public:
  virtual int MemberFunction();
}
```

After:

``` c++
class Foo {
public:
  virtual int pre_member_function_post();
}
```

## The default mapping table of Hungarian Notation

In Hungarian notation, a variable name starts with a group of lower-case
letters which are mnemonics for the type or purpose of that variable,
followed by whatever name the programmer has chosen; this last part is
sometimes distinguished as the given name. The first character of the
given name can be capitalized to separate it from the type indicators
(see also CamelCase). Otherwise the case of this character denotes
scope.

The following table is the default mapping table of Hungarian Notation
which maps Decl to its prefix string. You can also have your own style
in config file.

<table>
<thead>
<tr>
<th>Primitive Type</th>
<th></th>
<th></th>
<th></th>
<th>Microsoft Type</th>
<th></th>
</tr>
</thead>
<tbody>
<tr>
<td><blockquote>
<p>Type</p>
</blockquote></td>
<td>Prefix</td>
<td>Type</td>
<td>Prefix</td>
<td>Type</td>
<td>Prefix</td>
</tr>
<tr>
<td><hr /></td>
<td><hr /></td>
<td><hr /></td>
<td><hr /></td>
<td><hr /></td>
<td><hr /></td>
</tr>
<tr>
<td>int8_t</td>
<td>i8</td>
<td>signed int</td>
<td>si</td>
<td>BOOL</td>
<td>b</td>
</tr>
<tr>
<td>int16_t</td>
<td>i16</td>
<td>signed short</td>
<td>ss</td>
<td>BOOLEAN</td>
<td>b</td>
</tr>
<tr>
<td>int32_t</td>
<td>i32</td>
<td>signed short int</td>
<td>ssi</td>
<td>BYTE</td>
<td>by</td>
</tr>
<tr>
<td>int64_t</td>
<td>i64</td>
<td>signed long long int</td>
<td>slli</td>
<td>CHAR</td>
<td>c</td>
</tr>
<tr>
<td>uint8_t</td>
<td>u8</td>
<td>signed long long</td>
<td>sll</td>
<td>UCHAR</td>
<td>uc</td>
</tr>
<tr>
<td>uint16_t</td>
<td>u16</td>
<td>signed long int</td>
<td>sli</td>
<td>SHORT</td>
<td>s</td>
</tr>
<tr>
<td>uint32_t</td>
<td>u32</td>
<td>signed long</td>
<td>sl</td>
<td>USHORT</td>
<td>us</td>
</tr>
<tr>
<td>uint64_t</td>
<td>u64</td>
<td>signed</td>
<td>s</td>
<td>WORD</td>
<td>w</td>
</tr>
<tr>
<td>char8_t</td>
<td>c8</td>
<td>unsigned long long int</td>
<td>ulli</td>
<td>DWORD</td>
<td>dw</td>
</tr>
<tr>
<td>char16_t</td>
<td>c16</td>
<td>unsigned long long</td>
<td>ull</td>
<td>DWORD32</td>
<td>dw32</td>
</tr>
<tr>
<td>char32_t</td>
<td>c32</td>
<td>unsigned long int</td>
<td>uli</td>
<td>DWORD64</td>
<td>dw64</td>
</tr>
<tr>
<td>float</td>
<td>f</td>
<td>unsigned long</td>
<td>ul</td>
<td>LONG</td>
<td>l</td>
</tr>
<tr>
<td>double</td>
<td>d</td>
<td>unsigned short int</td>
<td>usi</td>
<td>ULONG</td>
<td>ul</td>
</tr>
<tr>
<td>char</td>
<td>c</td>
<td>unsigned short</td>
<td>us</td>
<td>ULONG32</td>
<td>ul32</td>
</tr>
<tr>
<td>bool</td>
<td>b</td>
<td>unsigned int</td>
<td>ui</td>
<td>ULONG64</td>
<td>ul64</td>
</tr>
<tr>
<td><span id="bool">Bool</span></td>
<td>b</td>
<td>unsigned char</td>
<td>uc</td>
<td>ULONGLONG</td>
<td>ull</td>
</tr>
<tr>
<td>int</td>
<td>i</td>
<td>unsigned</td>
<td>u</td>
<td>HANDLE</td>
<td>h</td>
</tr>
<tr>
<td>size_t</td>
<td>n</td>
<td>long long int</td>
<td>lli</td>
<td>INT</td>
<td>i</td>
</tr>
<tr>
<td>short</td>
<td>s</td>
<td>long double</td>
<td>ld</td>
<td>INT8</td>
<td>i8</td>
</tr>
<tr>
<td>signed</td>
<td>i</td>
<td>long long</td>
<td>ll</td>
<td>INT16</td>
<td>i16</td>
</tr>
<tr>
<td>unsigned</td>
<td>u</td>
<td>long int</td>
<td>li</td>
<td>INT32</td>
<td>i32</td>
</tr>
<tr>
<td>long</td>
<td>l</td>
<td>long</td>
<td>l</td>
<td>INT64</td>
<td>i64</td>
</tr>
<tr>
<td>long long</td>
<td>ll</td>
<td>ptrdiff_t</td>
<td>p</td>
<td>UINT</td>
<td>ui</td>
</tr>
<tr>
<td>unsigned long</td>
<td>ul</td>
<td>void</td>
<td><em>none</em></td>
<td>UINT8</td>
<td>u8</td>
</tr>
<tr>
<td>long double</td>
<td>ld</td>
<td></td>
<td></td>
<td>UINT16</td>
<td>u16</td>
</tr>
<tr>
<td>ptrdiff_t</td>
<td>p</td>
<td></td>
<td></td>
<td>UINT32</td>
<td>u32</td>
</tr>
<tr>
<td>wchar_t</td>
<td>wc</td>
<td></td>
<td></td>
<td>UINT64</td>
<td>u64</td>
</tr>
<tr>
<td>short int</td>
<td>si</td>
<td></td>
<td></td>
<td>PVOID</td>
<td>p</td>
</tr>
<tr>
<td>short</td>
<td>s</td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

**There are more trivial options for Hungarian Notation:**

**HungarianNotation.General.**\*  
Options are not belonging to any specific Decl.

**HungarianNotation.CString.**\*  
Options for NULL-terminated string.

**HungarianNotation.DerivedType.**\*  
Options for derived types.

**HungarianNotation.PrimitiveType.**\*  
Options for primitive types.

**HungarianNotation.UserDefinedType.**\*  
Options for user-defined types.

## Options for Hungarian Notation

- `HungarianNotation.General.TreatStructAsClass`
- `HungarianNotation.DerivedType.Array`
- `HungarianNotation.DerivedType.Pointer`
- `HungarianNotation.DerivedType.FunctionPointer`
- `HungarianNotation.CString.CharPointer`
- `HungarianNotation.CString.CharArray`
- `HungarianNotation.CString.WideCharPointer`
- `HungarianNotation.CString.WideCharArray`
- `HungarianNotation.PrimitiveType.*`
- `HungarianNotation.UserDefinedType.*`

<div class="option">

HungarianNotation.General.TreatStructAsClass

When defined, the check will treat naming of struct as a class. The
default value is <span class="title-ref">false</span>.

</div>

<div class="option">

HungarianNotation.DerivedType.Array

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">a</span>.

</div>

<div class="option">

HungarianNotation.DerivedType.Pointer

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">p</span>.

</div>

<div class="option">

HungarianNotation.DerivedType.FunctionPointer

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">fn</span>.

</div>

Before:

``` c++
// Array
int DataArray[2] = {0};

// Pointer
void *DataBuffer = NULL;

// FunctionPointer
typedef void (*FUNC_PTR)();
FUNC_PTR FuncPtr = NULL;
```

After:

``` c++
// Array
int aDataArray[2] = {0};

// Pointer
void *pDataBuffer = NULL;

// FunctionPointer
typedef void (*FUNC_PTR)();
FUNC_PTR fnFuncPtr = NULL;
```

<div class="option">

HungarianNotation.CString.CharPointer

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">sz</span>.

</div>

<div class="option">

HungarianNotation.CString.CharArray

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">sz</span>.

</div>

<div class="option">

HungarianNotation.CString.WideCharPointer

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">wsz</span>.

</div>

<div class="option">

HungarianNotation.CString.WideCharArray

When defined, the check will ensure variable name will add the prefix
with the given string. The default prefix is
<span class="title-ref">wsz</span>.

</div>

Before:

``` c++
// CharPointer
const char *NamePtr = "Name";

// CharArray
const char NameArray[] = "Name";

// WideCharPointer
const wchar_t *WideNamePtr = L"Name";

// WideCharArray
const wchar_t WideNameArray[] = L"Name";
```

After:

``` c++
// CharPointer
const char *szNamePtr = "Name";

// CharArray
const char szNameArray[] = "Name";

// WideCharPointer
const wchar_t *wszWideNamePtr = L"Name";

// WideCharArray
const wchar_t wszWideNameArray[] = L"Name";
```

<div class="option">

HungarianNotation.PrimitiveType.\*

When defined, the check will ensure variable name of involved primitive
types will add the prefix with the given string. The default prefixes
are defined in the default mapping table.

</div>

<div class="option">

HungarianNotation.UserDefinedType.\*

When defined, the check will ensure variable name of involved primitive
types will add the prefix with the given string. The default prefixes
are defined in the default mapping table.

</div>

Before:

``` c++
int8_t   ValueI8      = 0;
int16_t  ValueI16     = 0;
int32_t  ValueI32     = 0;
int64_t  ValueI64     = 0;
uint8_t  ValueU8      = 0;
uint16_t ValueU16     = 0;
uint32_t ValueU32     = 0;
uint64_t ValueU64     = 0;
float    ValueFloat   = 0.0;
double   ValueDouble  = 0.0;
ULONG    ValueUlong   = 0;
DWORD    ValueDword   = 0;
```

After:

``` c++
int8_t   i8ValueI8    = 0;
int16_t  i16ValueI16  = 0;
int32_t  i32ValueI32  = 0;
int64_t  i64ValueI64  = 0;
uint8_t  u8ValueU8    = 0;
uint16_t u16ValueU16  = 0;
uint32_t u32ValueU32  = 0;
uint64_t u64ValueU64  = 0;
float    fValueFloat  = 0.0;
double   dValueDouble = 0.0;
ULONG    ulValueUlong = 0;
DWORD    dwValueDword = 0;
```
