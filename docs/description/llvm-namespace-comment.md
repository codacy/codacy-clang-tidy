llvm-namespace-comment
======================

<span class="title-ref">google-readability-namespace-comments</span>
redirects here as an alias for this check.

Checks that long namespaces have a closing comment.

<https://llvm.org/docs/CodingStandards.html#namespace-indentation>

<https://google.github.io/styleguide/cppguide.html#Namespaces>

    namespace n1 {
    void f();
    }

    // becomes

    namespace n1 {
    void f();
    }  // namespace n1

Options
-------

ShortNamespaceLines

Requires the closing brace of the namespace definition to be followed by
a closing comment if the body of the namespace has more than <span
class="title-ref">ShortNamespaceLines</span> lines of code. The value is
an unsigned integer that defaults to <span class="title-ref">1U</span>.

SpacesBeforeComments

An unsigned integer specifying the number of spaces before the comment
closing a namespace definition. Default is <span
class="title-ref">1U</span>.
