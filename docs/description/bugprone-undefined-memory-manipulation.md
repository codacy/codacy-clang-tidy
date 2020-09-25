bugprone-undefined-memory-manipulation
======================================

Finds calls of memory manipulation functions `memset()`, `memcpy()` and
`memmove()` on not TriviallyCopyable objects resulting in undefined
behavior.
