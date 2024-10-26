package org.bong.search.core.processor.filter

import io.kotest.core.spec.style.FunSpec
import org.bong.search.core.processor.TestItemData


class FilterTest : FunSpec({

    test("FilterStrategy는 주어진 조건에 맞게 전달받은 Item 리스트를 필터링한다") {
        // given
        val items = TestItemData.itemListFixture()

        // when
        val filteredItems =  FilterStrategy(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        ).execute(items)

        // then
        filteredItems.forEach {
            assert(it.age > 30)
        }
    }

    test("Filter는 1개 이상의 FilterStrategy 적용하여 Item 리스트를 필터링한다.") {
        // given
        val items = TestItemData.itemListFixture()
        val filterGraterThan30 = FilterStrategy(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        )
        val filterAddressIsSeoul = FilterStrategy(
            TestItemData::address,
            "Seoul",
            ComparisonOperator.EQUALS
        )

        // when
        val result = Filter(listOf(filterGraterThan30, filterAddressIsSeoul))
            .execute(items)

        // then
        result.forEach {
            assert(it.age > 30)
            assert(it.address == "Seoul")
        }
    }
})
