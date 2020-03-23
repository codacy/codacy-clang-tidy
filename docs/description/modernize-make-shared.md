modernize-make-shared
=====================

This check finds the creation of `std::shared_ptr` objects by explicitly
calling the constructor and a `new` expression, and replaces it with a
call to `std::make_shared`.

.. code-block:: c++

auto my\_ptr = std::shared\_ptr`<MyPair>`{=html}(new MyPair(1, 2));

// becomes

auto my\_ptr = std::make\_shared`<MyPair>`{=html}(1, 2);

This check also finds calls to `std::shared_ptr::reset()` with a `new`
expression, and replaces it with a call to `std::make_shared`.

.. code-block:: c++

my\_ptr.reset(new MyPair(1, 2));

// becomes

my\_ptr = std::make\_shared`<MyPair>`{=html}(1, 2);

Options
-------

.. option:: MakeSmartPtrFunction

A string specifying the name of make-shared-ptr function. Default is
`std::make_shared`.

.. option:: MakeSmartPtrFunctionHeader

A string specifying the corresponding header of make-shared-ptr
function. Default is `memory`.

.. option:: IncludeStyle

A string specifying which include-style is used, `llvm` or `google`.
Default is `llvm`.

.. option:: IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is `1`.
