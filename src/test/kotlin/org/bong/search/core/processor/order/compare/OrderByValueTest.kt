package org.bong.search.core.processor.order.compare

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.bong.search.core.keyword.KeywordMetadata
import org.bong.search.core.keyword.Source
import org.bong.search.core.keyword.SourceType
import org.bong.search.core.processor.TestItemData
import org.bong.search.core.processor.operator.ComparisonOperator

class OrderByValueTest : FunSpec({

    test("OrderByValue는 SearchedKeyword 인터페이스의 구체 타입에 대해 주어진 조건에 맞게 Item 리스트를 정렬한다") {
        // given
        val items =  listOf(
            TestItemData(TestItemData.httpAndNaverMetaData, "Bob", 30, "Busan"),
            TestItemData(TestItemData.httpAndNaverMetaData,"Alice", 20, "Seoul"),
            TestItemData(TestItemData.httpAndNaverMetaData,"Charlie", 40, "Daegu"),
            TestItemData(TestItemData.httpAndNaverMetaData,"Ally", 30, "Seoul")
        )
        val orderingValue = "Al"

        // when
        val orderedItems = CompareByValue<TestItemData, String>(
            orderingValue,
            ComparisonOperator.CONTAINS
        ) { it.name }.execute(items)


        // then
        orderedItems.first().name shouldContain orderingValue
    }

    test("OrderByValue는 SearchedKeyword의 메타데이터에 대해 주어진 조건에 맞게 Item 리스트를 정렬한다") {
        // given
        val naverMetaData = KeywordMetadata(Source(SourceType.HTTP, "naver"))
        val kakaoMetaData = KeywordMetadata(Source(SourceType.HTTP, "kakao"))
        val items =  listOf(
            TestItemData(naverMetaData, "Bob", 30, "Busan"),
            TestItemData(kakaoMetaData,"Alice", 20, "Seoul"),
            TestItemData(kakaoMetaData,"Charlie", 40, "Daegu"),
            TestItemData(kakaoMetaData,"Ally", 30, "Seoul")
        )
        val fromOrdering = "kakao"

        // when
        val orderedItems = CompareByValue<TestItemData, String>(
            fromOrdering,
            ComparisonOperator.CONTAINS,
        ) {
            it.metaData.source.from
        }.execute(items)


        // then
        orderedItems.first().metaData.source.from shouldContain fromOrdering
    }
})