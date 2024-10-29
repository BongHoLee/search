package org.bong.search.core.processor.filter

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.bong.search.core.processor.TestItemData
import org.bong.search.core.processor.operator.ComparisonOperator


class FilterTest : FunSpec({

    test("LeafOperand는 주어진 조건에 맞는 Item만 필터링한다") {
        // given
        val items = TestItemData.itemListFixture()

        // when
        val filteredItems =  LeafOperand(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        ).execute(items.toSet())

        // then
        filteredItems.forEach {
            assert(it.age > 30)
        }
    }

    test("AndOperand는 주어진 두개의 조건을 모두 만족하는 Item만 필터링한다") {
        // given
        val items = TestItemData.itemListFixture()
        val filterGraterThan30 = LeafOperand(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        )
        val filterAddressIsSeoul = LeafOperand(
            TestItemData::address,
            "Seoul",
            ComparisonOperator.EQUALS
        )

        // when
        val filteredItems = AndOperand(
            filterGraterThan30,
            filterAddressIsSeoul
        ).execute(items.toSet())

        // then
        filteredItems.forEach {
            it.age shouldBeGreaterThan  30
            it.address shouldBe "Seoul"
        }
    }

    test("OrOperand는 주어진 두개의 조건 중 하나라도 만족하는 Item만 필터링한다") {
        // given
        val items = TestItemData.itemListFixture()
        val filterGraterThan30 = LeafOperand(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        )
        val filterAddressIsSeoul = LeafOperand(
            TestItemData::address,
            "Seoul",
            ComparisonOperator.EQUALS
        )

        // when
        val filteredItems = OrOperand(
            filterGraterThan30,
            filterAddressIsSeoul
        ).execute(items.toSet())

        // then
        filteredItems.forEach {

            (it.age > 30 || it.address == "Seoul").shouldBeTrue()
        }
    }

    test("Filter는 주어진 여러 Operand를 조합한 결과를 반환한다") {
        // given
        val items = TestItemData.itemListFixture()
        val filterGraterThan30 = LeafOperand(
            TestItemData::age,
            30,
            ComparisonOperator.GREATER_THAN
        )
        val filterAddressIsSeoul = LeafOperand(
            TestItemData::address,
            "Seoul",
            ComparisonOperator.EQUALS
        )
        val filterGraterThan40 = LeafOperand(
            TestItemData::age,
            40,
            ComparisonOperator.GREATER_THAN
        )

        // when - 30살 초과이거나 서울에 거주하는 사람 중 40살 초과인 사람
        val filteredItems = Filter(
            AndOperand(
                left = OrOperand(filterGraterThan30, filterAddressIsSeoul),
                right = filterGraterThan40
            )
        ).execute(items)

        // then
        filteredItems.forEach {
            it shouldBe TestItemData("Kim", 50, "Seoul")
        }
    }
})
