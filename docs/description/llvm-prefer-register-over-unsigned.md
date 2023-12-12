clang-tidy - llvm-prefer-register-over-unsigned

</div>

# llvm-prefer-register-over-unsigned

Finds historical use of `unsigned` to hold vregs and physregs and
rewrites them to use `Register`.

Currently this works by finding all variables of unsigned integer type
whose initializer begins with an implicit cast from `Register` to
`unsigned`.

``` c++
void example(MachineOperand &MO) {
  unsigned Reg = MO.getReg();
  ...
}
```

becomes:

``` c++
void example(MachineOperand &MO) {
  Register Reg = MO.getReg();
  ...
}
```
