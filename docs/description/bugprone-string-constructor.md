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
```

Creating an empty string from constructors with parameters is considered
suspicious. The programmer should use the empty constructor instead.

Examples:

``` c++
std::string("test", 0);   // Creation of an empty string.
```

## Options

<div class="option">

WarnOnLargeLength

When non-zero, the check will warn on a string with a length greater
than <span class="title-ref">LargeLengthThreshold</span>. Default is
<span class="title-ref">1</span>.

</div>

<div class="option">

LargeLengthThreshold

An integer specifying the large length threshold. Default is
<span class="title-ref">0x800000</span>.

</div>
