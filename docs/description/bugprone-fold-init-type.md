bugprone-fold-init-type
=======================

The check flags type mismatches in
[folds](https://en.wikipedia.org/wiki/Fold_(higher-order_function)) like
`std::accumulate` that might result in loss of precision.
`std::accumulate` folds an input range into an initial value using the
type of the latter, with `operator+` by default. This can cause loss of
precision through:

-   Truncation: The following code uses a floating point range and an
    int initial value, so trucation will happen at every application of
    `operator+` and the result will be <span class="title-ref">0</span>,
    which might not be what the user expected.

<!-- -->

    auto a = {0.5f, 0.5f, 0.5f, 0.5f};
    return std::accumulate(std::begin(a), std::end(a), 0);

-   Overflow: The following code also returns <span
    class="title-ref">0</span>.

<!-- -->

    auto a = {65536LL * 65536 * 65536};
    return std::accumulate(std::begin(a), std::end(a), 0);
