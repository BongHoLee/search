package org.bong.search.core.processor.filter

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.bong.search.core.processor.TestItemData
import org.bong.search.core.processor.operator.ComparisonOperator

class FilterSpec : DescribeSpec({
    describe("Filter") {
        context("LeafOperand") {
            it("주어진 조건에 맞는 Item만 필터링한다") {
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
        }

        context("AndOperand") {
            it("주어진 두개의 조건을 모두 만족하는 Item만 필터링한다") {
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
        }

        context("OrOperand") {
            it("주어진 두개의 조건 중 하나라도 만족하는 Item만 필터링한다") {
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
                    assert(it.age > 30 || it.address == "Seoul")
                }
            }
        }
    }
})