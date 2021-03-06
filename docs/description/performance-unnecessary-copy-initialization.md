performance-unnecessary-copy-initialization
===========================================

Finds local variable declarations that are initialized using the copy
constructor of a non-trivially-copyable type but it would suffice to
obtain a const reference.

The check is only applied if it is safe to replace the copy by a const
reference. This is the case when the variable is const qualified or when
it is only used as a const, i.e. only const methods or operators are
invoked on it, or it is used as const reference or value argument in
constructors or function calls.

Example:

    const string& constReference();
    void Function() {
      // The warning will suggest making this a const reference.
      const string UnnecessaryCopy = constReference();
    }

    struct Foo {
      const string& name() const;
    };
    void Function(const Foo& foo) {
      // The warning will suggest making this a const reference.
      string UnnecessaryCopy1 = foo.name();
      UnnecessaryCopy1.find("bar");

      // The warning will suggest making this a const reference.
      string UnnecessaryCopy2 = UnnecessaryCopy1;
      UnnecessaryCopy2.find("bar");
    }

Options
-------

AllowedTypes

A semicolon-separated list of names of types allowed to be initialized
by copying. Regular expressions are accepted, e.g. <span
class="title-ref">\[Rr\]ef(erence)?$</span> matches every type with
suffix <span class="title-ref">Ref</span>, <span
class="title-ref">ref</span>, <span class="title-ref">Reference</span>
and <span class="title-ref">reference</span>. The default is empty.
