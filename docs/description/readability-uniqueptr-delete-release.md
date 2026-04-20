clang-tidy - readability-uniqueptr-delete-release

</div>

# readability-uniqueptr-delete-release

Replace `delete <unique_ptr>.release()` with `<unique_ptr> = nullptr`.
The latter is shorter, simpler and does not require use of raw pointer
APIs.

``` c++
std::unique_ptr<int> P;
delete P.release();

// becomes

std::unique_ptr<int> P;
P = nullptr;
```

## Options

<div class="option">

PreferResetCall

If <span class="title-ref">true</span>, refactor by calling the reset
member function instead of assigning to `nullptr`. Default value is
<span class="title-ref">false</span>.

``` c++
std::unique_ptr<int> P;
delete P.release();

// becomes

std::unique_ptr<int> P;
P.reset();
```

</div>
