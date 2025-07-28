clang-tidy - portability-restrict-system-includes

</div>

# portability-restrict-system-includes

Checks to selectively allow or disallow a configurable list of system
headers.

For example:

In order to **only** allow <span class="title-ref">zlib.h</span> from
the system you would set the options to
<span class="title-ref">-\*,zlib.h</span>.

``` c++
#include <curses.h>       // Bad: disallowed system header.
#include <openssl/ssl.h>  // Bad: disallowed system header.
#include <zlib.h>         // Good: allowed system header.
#include "src/myfile.h"   // Good: non-system header always allowed.
```

In order to allow everything **except**
<span class="title-ref">zlib.h</span> from the system you would set the
options to <span class="title-ref">\*,-zlib.h</span>.

``` c++
#include <curses.h>       // Good: allowed system header.
#include <openssl/ssl.h>  // Good: allowed system header.
#include <zlib.h>         // Bad: disallowed system header.
#include "src/myfile.h"   // Good: non-system header always allowed.
```

Since the options support globbing you can use wildcarding to allow
groups of headers.

<span class="title-ref">-\*,openssl/\*.h</span> will allow all openssl
headers but disallow any others.

``` c++
#include <curses.h>       // Bad: disallowed system header.
#include <openssl/ssl.h>  // Good: allowed system header.
#include <openssl/rsa.h>  // Good: allowed system header.
#include <zlib.h>         // Bad: disallowed system header.
#include "src/myfile.h"   // Good: non-system header always allowed.
```

## Options

<div class="option">

Includes

A string containing a comma separated glob list of allowed include
filenames. Similar to the -checks glob list for running clang-tidy
itself, the two wildcard characters are
<span class="title-ref">\*</span> and <span class="title-ref">-</span>,
to include and exclude globs, respectively. The default is
<span class="title-ref">\*</span>, which allows all includes.

</div>
