clang-tidy - objc-nsinvocation-argument-lifetime

</div>

# objc-nsinvocation-argument-lifetime

Finds calls to `NSInvocation` methods under ARC that don't have proper
argument object lifetimes. When passing Objective-C objects as
parameters to the `NSInvocation` methods `getArgument:atIndex:` and
`getReturnValue:`, the values are copied by value into the argument
pointer, which leads to incorrect releasing behavior if the object
pointers are not declared `__unsafe_unretained`.

For code:

``` objc
id arg;
[invocation getArgument:&arg atIndex:2];

__strong id returnValue;
[invocation getReturnValue:&returnValue];
```

The fix will be:

``` objc
__unsafe_unretained id arg;
[invocation getArgument:&arg atIndex:2];

__unsafe_unretained id returnValue;
[invocation getReturnValue:&returnValue];
```

The check will warn on being passed instance variable references that
have lifetimes other than `__unsafe_unretained`, but does not propose a
fix:

``` objc
// "id _returnValue" is declaration of instance variable of class.
[invocation getReturnValue:&self->_returnValue];
```
