package org.bong.search.core.processor.order.compare

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.bong.search.core.processor.TestItemData

class CountCompareTest : FunSpec({

        test("CountCompare는 주어진 프로퍼티 값이 동일한 갯수를 우선순위로 Item을 정렬한다.") {
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
            val orderedItems = CompareByCount(
                TestItemData::address,
                NothingCompare()
            ).execute(items)

            // then
            orderedItems.size shouldBe 3
            orderedItems[0].address shouldBe "Seoul"
            orderedItems[1].address shouldBe "Daegu"
            orderedItems[2].address shouldBe "Busan"
        }
})