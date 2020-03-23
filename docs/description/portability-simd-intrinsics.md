# portability-simd-intrinsics

Finds SIMD intrinsics calls and suggests `std::experimental::simd`
([P0214](https://wg21.link/p0214)) alternatives.

If the option `Suggest` is set to non-zero, for

``` c++
_mm_add_epi32(a, b); // x86
vec_add(a, b);       // Power
```

the check suggests an alternative: `operator+` on
`std::experimental::simd` objects.

Otherwise, it just complains the intrinsics are non-portable (and there
are [P0214](https://wg21.link/p0214) alternatives).

Many architectures provide SIMD operations (e.g. x86 SSE/AVX, Power
AltiVec/VSX, ARM NEON). It is common that SIMD code implementing the
same algorithm, is written in multiple target-dispatching pieces to
optimize for different architectures or micro-architectures.

The C++ standard proposal [P0214](https://wg21.link/p0214) and its
extensions cover many common SIMD operations. By migrating from
target-dependent intrinsics to [P0214](https://wg21.link/p0214)
operations, the SIMD code can be simplified and pieces for different
targets can be unified.

Refer to [P0214](https://wg21.link/p0214) for introduction and
motivation for the data-parallel standard library.

## Options

<div class="option">

Suggest

If this option is set to non-zero (default is
<span class="title-ref">0</span>), the check will suggest
[P0214](https://wg21.link/p0214) alternatives, otherwise it only points
out the intrinsic function is non-portable.

</div>

<div class="option">

Std

The namespace used to suggest [P0214](https://wg21.link/p0214)
alternatives. If not specified, <span class="title-ref">std::</span> for
<span class="title-ref">-std=c++2a</span> and
<span class="title-ref">std::experimental::</span> for
<span class="title-ref">-std=c++11</span>.

</div>
