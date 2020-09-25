llvm-twine-local
================

Looks for local `Twine` variables which are prone to use after frees and
should be generally avoided.

    static Twine Moo = Twine("bark") + "bah";

    // becomes

    static std::string Moo = (Twine("bark") + "bah").str();
