modernize-make-unique
=====================

This check finds the creation of `std::unique_ptr` objects by explicitly
calling the constructor and a `new` expression, and replaces it with a
call to `std::make_unique`, introduced in C++14.

.. code-block:: c++

auto my\_ptr = std::unique\_ptr`<MyPair>`{=html}(new MyPair(1, 2));

// becomes

auto my\_ptr = std::make\_unique`<MyPair>`{=html}(1, 2);

This check also finds calls to `std::unique_ptr::reset()` with a `new`
expression, and replaces it with a call to `std::make_unique`.

.. code-block:: c++

my\_ptr.reset(new MyPair(1, 2));

// becomes

my\_ptr = std::make\_unique`<MyPair>`{=html}(1, 2);

Options
-------

.. option:: MakeSmartPtrFunction

A string specifying the name of make-unique-ptr function. Default is
`std::make_unique`.

.. option:: MakeSmartPtrFunctionHeader

A string specifying the corresponding header of make-unique-ptr
function. Default is `memory`.

.. option:: IncludeStyle

A string specifying which include-style is used, `llvm` or `google`.
Default is `llvm`.

.. option:: IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is `1`.
