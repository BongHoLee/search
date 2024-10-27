package org.bong.search.core.processor.order.compare

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import org.bong.search.core.processor.TestItemData

class CompareByPropertyTest : FunSpec({

    test("CompareByProperty는 주어진 프로퍼티 값이 동일한 갯수를 우선순위로 Item을 정렬한다.") {
        // given
        val items =  listOf(
            TestItemData("Bob", 30, "Busan"),
            TestItemData("Alice", 20, "Seoul"),
            TestItemData("Lee", 20, "Seoul"),
            TestItemData("Charlie", 40, "Daegu"),
            TestItemData("Kim", 40, "Daegu"),
            TestItemData("Hong", 40, "Daegu"),
            TestItemData("Ally", 30, "Seoul")
        )

        // when
        val orderedItems = CompareByProperty(
            TestItemData::age,
            NothingCompare()
        ).execute(items)

        // then
        var last = 100
        orderedItems.forEach {
            it.age shouldBeLessThanOrEqual  last
            last = it.age
        }

    }
})