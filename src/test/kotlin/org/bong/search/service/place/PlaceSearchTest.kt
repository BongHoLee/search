package org.bong.search.service.place

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class PlaceSearchTest : FunSpec({

    test("장소 검색 시 검색어가 빈 문자열이면 예외가 발생한다") {
        // given
        val keyword = ""

        // when
        val exception = shouldThrow<IllegalArgumentException> {
            SearchingPlace(keyword)
        }

        // then
        exception.message shouldBe "keyword must not be empty"
    }

})
