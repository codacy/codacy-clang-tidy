readability-function-size
=========================

<span class="title-ref">google-readability-function-size</span>
redirects here as an alias for this check.

Checks for large functions based on various metrics.

Options
-------

LineThreshold

Flag functions exceeding this number of lines. The default is <span
class="title-ref">-1</span> (ignore the number of lines).

StatementThreshold

Flag functions exceeding this number of statements. This may differ
significantly from the number of lines for macro-heavy code. The default
is <span class="title-ref">800</span>.

BranchThreshold

Flag functions exceeding this number of control statements. The default
is <span class="title-ref">-1</span> (ignore the number of branches).

ParameterThreshold

Flag functions that exceed a specified number of parameters. The default
is <span class="title-ref">-1</span> (ignore the number of parameters).

NestingThreshold

Flag compound statements which create next nesting level after <span
class="title-ref">NestingThreshold</span>. This may differ significantly
from the expected value for macro-heavy code. The default is <span
class="title-ref">-1</span> (ignore the nesting level).

VariableThreshold

Flag functions exceeding this number of variables declared in the body.
The default is <span class="title-ref">-1</span> (ignore the number of
variables). Please note that function parameters and variables declared
in lambdas, GNU Statement Expressions, and nested class inline functions
are not counted.
