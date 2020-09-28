abseil-no-namespace
===================

Ensures code does not open `namespace absl` as that violates Abseil's
compatibility guidelines. Code should not open `namespace absl` as that
conflicts with Abseil's compatibility guidelines and may result in
breakage.

Any code that uses:

    namespace absl {
     ...
    }

will be prompted with a warning.

See [the full Abseil compatibility guidelines &lt;https://
abseil.io/about/compatibility&gt;]() for more information.
