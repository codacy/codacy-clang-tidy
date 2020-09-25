cppcoreguidelines-macro-usage
=============================

Finds macro usage that is considered problematic because better language
constructs exist for the task.

The relevant sections in the C++ Core Guidelines are
[Enum.1](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#enum1-prefer-enumerations-over-macros),
[ES.30](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#es30-dont-use-macros-for-program-text-manipulation),
[ES.31](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#es31-dont-use-macros-for-constants-or-functions)
and
[ES.33](https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md#es33-if-you-must-use-macros-give-them-unique-names).

Options
-------

AllowedRegexp

A regular expression to filter allowed macros. For example <span
class="title-ref">DEBUG\*|LIBTORRENT\*|TORRENT\*|UNI\*</span> could be
applied to filter <span class="title-ref">libtorrent</span>. Default
value is <span class="title-ref">^DEBUG\_\*</span>.

CheckCapsOnly

Boolean flag to warn on all macros except those with CAPS\_ONLY names.
This option is intended to ease introduction of this check into older
code bases. Default value is <span class="title-ref">0</span>/<span
class="title-ref">false</span>.

IgnoreCommandLineMacros

Boolean flag to toggle ignoring command-line-defined macros. Default
value is <span class="title-ref">1</span>/<span
class="title-ref">true</span>.
