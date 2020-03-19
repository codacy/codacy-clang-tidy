modernize-use-bool-literals
===========================

Finds integer literals which are cast to `bool`.

.. code-block:: c++

bool p = 1; bool f = static\_cast<bool>(1);
std::ios\_base::sync\_with\_stdio(0); bool x = p ? 1 : 0;

// transforms to

bool p = true; bool f = true; std::ios\_base::sync\_with\_stdio(false);
bool x = p ? true : false;

Options
-------

.. option:: IgnoreMacros

If set to non-zero, the check will not give warnings inside macros.
Default is `1`.
