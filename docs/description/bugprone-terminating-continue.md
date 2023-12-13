clang-tidy - bugprone-terminating-continue

</div>

# bugprone-terminating-continue

Detects <span class="title-ref">do while</span> loops with a condition
always evaluating to false that have a
<span class="title-ref">continue</span> statement, as this
<span class="title-ref">continue</span> terminates the loop effectively.

``` c++
void f() {
do {
  // some code
  continue; // terminating continue
  // some other code
} while(false);
```
