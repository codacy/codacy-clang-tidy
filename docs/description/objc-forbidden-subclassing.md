clang-tidy - objc-forbidden-subclassing

</div>

# objc-forbidden-subclassing

Finds Objective-C classes which are subclasses of classes which are not
designed to be subclassed.

By default, includes a list of Objective-C classes which are publicly
documented as not supporting subclassing.

<div class="note">

<div class="title">

Note

</div>

Instead of using this check, for code under your control, you should add
`__attribute__((objc_subclassing_restricted))` before your `@interface`
declarations to ensure the compiler prevents others from subclassing
your Objective-C classes. See
<https://clang.llvm.org/docs/AttributeReference.html#objc-subclassing-restricted>

</div>

## Options

<div class="option">

ForbiddenSuperClassNames

Semicolon-separated list of names of Objective-C classes which do not
support subclassing.

Defaults to
<span class="title-ref">ABNewPersonViewController;ABPeoplePickerNavigationController;ABPersonViewController;ABUnknownPersonViewController;NSHashTable;NSMapTable;NSPointerArray;NSPointerFunctions;NSTimer;UIActionSheet;UIAlertView;UIImagePickerController;UITextInputMode;UIWebView</span>.

</div>
