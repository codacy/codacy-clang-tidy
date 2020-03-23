readability-uniqueptr-delete-release
====================================

Replace `delete <unique_ptr>.release()` with `<unique_ptr> = nullptr`.
The latter is shorter, simpler and does not require use of raw pointer
APIs.

.. code-block:: c++

std::unique\_ptr`<int>`{=html} P; delete P.release();

// becomes

std::unique\_ptr`<int>`{=html} P; P = nullptr;
