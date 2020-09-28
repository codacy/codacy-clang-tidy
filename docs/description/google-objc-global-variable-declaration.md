google-objc-global-variable-declaration
=======================================

Finds global variable declarations in Objective-C files that do not
follow the pattern of variable names in Google's Objective-C Style
Guide.

The corresponding style guide rule:
<https://google.github.io/styleguide/objcguide.html#variable-names>

All the global variables should follow the pattern of <span
class="title-ref">g\[A-Z\].\*</span> (variables) or <span
class="title-ref">k\[A-Z\].\*</span> (constants). The check will suggest
a variable name that follows the pattern if it can be inferred from the
original name.

For code:

    static NSString* myString = @"hello";

The fix will be:

    static NSString* gMyString = @"hello";

Another example of constant:

    static NSString* const myConstString = @"hello";

The fix will be:

    static NSString* const kMyConstString = @"hello";

However for code that prefixed with non-alphabetical characters like:

    static NSString* __anotherString = @"world";

The check will give a warning message but will not be able to suggest a
fix. The user need to fix it on his own.
