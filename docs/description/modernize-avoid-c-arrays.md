modernize-avoid-c-arrays
========================

`cppcoreguidelines-avoid-c-arrays` redirects here as an alias for this
check.

`hicpp-avoid-c-arrays` redirects here as an alias for this check.

Finds C-style array types and recommend to use `std::array<>` /
`std::vector<>`. All types of C arrays are diagnosed.

However, fix-it are potentially dangerous in header files and are
therefore not emitted right now.

.. code:: c++

int a\[\] = {1, 2}; // warning: do not declare C-style arrays, use
std::array&lt;&gt; instead

int b\[1\]; // warning: do not declare C-style arrays, use
std::array&lt;&gt; instead

void foo() { int c\[b\[0\]\]; // warning: do not declare C VLA arrays,
use std::vector&lt;&gt; instead }

template &lt;typename T, int Size&gt; class array { T d\[Size\]; //
warning: do not declare C-style arrays, use std::array&lt;&gt; instead

    int e[1]; // warning: do not declare C-style arrays, use std::array<> instead

};

array&lt;int\[4\], 2&gt; d; // warning: do not declare C-style arrays,
use std::array&lt;&gt; instead

using k = int\[4\]; // warning: do not declare C-style arrays, use
std::array&lt;&gt; instead

However, the `extern "C"` code is ignored, since it is common to share
such headers between C code, and C++ code.

.. code:: c++

// Some header extern “C” {

int f\[\] = {1, 2}; // not diagnosed

int j\[1\]; // not diagnosed

inline void bar() { { int j\[j\[0\]\]; // not diagnosed } }

}

Similarly, the `main()` function is ignored. Its second and third
parameters can be either `char* argv[]` or `char** argv`, but can not be
`std::array<>`.