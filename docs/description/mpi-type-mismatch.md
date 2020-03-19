mpi-type-mismatch
=================

This check verifies if buffer type and MPI (Message Passing Interface)
datatype pairs match for used MPI functions. All MPI datatypes defined
by the MPI standard (3.1) are verified by this check. User defined
typedefs, custom MPI datatypes and null pointer constants are skipped,
in the course of verification.

Example:

.. code-block:: c++

// In this case, the buffer type matches MPI datatype. char buf;
MPI\_Send(&buf, 1, MPI\_CHAR, 0, 0, MPI\_COMM\_WORLD);

// In the following case, the buffer type does not match MPI datatype.
int buf; MPI\_Send(&buf, 1, MPI\_CHAR, 0, 0, MPI\_COMM\_WORLD);
