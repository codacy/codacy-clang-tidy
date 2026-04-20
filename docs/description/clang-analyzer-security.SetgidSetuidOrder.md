clang-tidy - clang-analyzer-security.SetgidSetuidOrder

</div>

<div class="meta"
http-equiv=refresh="5;URL=https://clang.llvm.org/docs/analyzer/checkers.html#security-setgidsetuidorder-c">

</div>

# clang-analyzer-security.SetgidSetuidOrder

The checker checks for sequences of `setuid(getuid())` and
`setgid(getgid())` calls (in this order). If such a sequence is found
and there is no other privilege-changing function call (`seteuid`,
`setreuid`, `setresuid` and the GID versions of these) in between, a
warning is generated. The checker finds only exactly `setuid(getuid())`
calls (and the GID versions), not for example if the result of
`getuid()` is stored in a variable.

The
<span class="title-ref">clang-analyzer-security.SetgidSetuidOrder</span>
check is an alias, please see [Clang Static Analyzer Available
Checkers](https://clang.llvm.org/docs/analyzer/checkers.html#security-setgidsetuidorder-c)
for more information.
