clang-tidy - objc-assert-equals

</div>

# objc-assert-equals

Finds improper usages of <span class="title-ref">XCTAssertEqual</span>
and <span class="title-ref">XCTAssertNotEqual</span> and replaces them
with <span class="title-ref">XCTAssertEqualObjects</span> or
<span class="title-ref">XCTAssertNotEqualObjects</span>.

This makes tests less fragile, as many improperly rely on pointer
equality for strings that have equal values. This assumption is not
guaranteed by the language.
