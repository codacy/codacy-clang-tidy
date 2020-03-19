readability-redundant-smartptr-get
==================================

Find and remove redundant calls to smart pointer’s `.get()` method.

Examples:

.. code-block:: c++

ptr.get()-&gt;Foo() ==&gt; ptr-&gt;Foo() *ptr.get() ==&gt; *ptr
\*ptr-&gt;get() ==&gt; \*\*ptr if (ptr.get() == nullptr) … =&gt; if (ptr
== nullptr) …

.. option:: IgnoreMacros

If this option is set to non-zero (default is `1`), the check will not
warn about calls inside macros.
