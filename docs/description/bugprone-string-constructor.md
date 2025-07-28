clang-tidy - bugprone-string-constructor

</div>

# bugprone-string-constructor

Finds string constructors that are suspicious and probably errors.

A common mistake is to swap parameters to the 'fill' string-constructor.

Examples:

``` c++
std::string str('x', 50); // should be str(50, 'x')
```

Calling the string-literal constructor with a length bigger than the
literal is suspicious and adds extra random characters to the string.

Examples:

``` c++
std::string("test", 200);   // Will include random characters after "test".
std::string_view("test", 200);
```

Creating an empty string from constructors with parameters is considered
suspicious. The programmer should use the empty constructor instead.

Examples:

``` c++
std::string("test", 0);   // Creation of an empty string.
std::string_view("test", 0);
```

## Options

<div class="option">

WarnOnLargeLength

When <span class="title-ref">true</span>, the check will warn on a
string with a length greater than `LargeLengthThreshold`. Default is
<span class="title-ref">true</span>.

</div>

<div class="option">

LargeLengthThreshold

An integer specifying the large length threshold. Default is
<span class="title-ref">0x800000</span>.

</div>

<div class="option">

StringNames

Default is
<span class="title-ref">::std::basic_string;::std::basic_string_view</span>.

Semicolon-delimited list of class names to apply this check to. By
default <span class="title-ref">::std::basic_string</span> applies to
`std::string` and `std::wstring`. Set to e.g.
<span class="title-ref">::std::basic_string;llvm::StringRef;QString</span>
to perform this check on custom classes.

</div>
