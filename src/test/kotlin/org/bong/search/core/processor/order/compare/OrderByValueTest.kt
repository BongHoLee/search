package org.bong.search.core.processor.order.compare

import io.kotest.core.spec.style.FunSpec
import org.bong.search.core.processor.TestItemData
import org.bong.search.core.processor.operator.ComparisonOperator

class OrderByValueTest : FunSpec({

    test("OrderByValue는 주어진 조건에 맞게 전달받은 Item 리스트를 정렬한다") {
        // given
        val items =  listOf(
            TestItemData("Bob", 30, "Busan"),
            TestItemData("Alice", 20, "Seoul"),
            TestItemData("Charlie", 40, "Daegu"),
            TestItemData("Ally", 30, "Seoul")
        )
        val orderingValue = "Al"

        // when
        val orderedItems = CompareByValue(
            TestItemData::name,
            orderingValue,
            ComparisonOperator.CONTAINS
        ).execute(items)

        // then
        assert(orderedItems.first().name.contains(orderingValue))
    }
})