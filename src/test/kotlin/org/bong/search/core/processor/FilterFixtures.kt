package org.bong.search.core.processor

import org.bong.search.core.keyword.KeywordMetadata
import org.bong.search.core.keyword.SearchedKeyword
import org.bong.search.core.keyword.Source
import org.bong.search.core.keyword.SourceType

data class TestItemData(
    override val metaData: KeywordMetadata,
    val name: String,
    val age: Int,
    val address: String
) : SearchedKeyword {
    companion object {

        val httpAndNaverMetaData = KeywordMetadata(Source(SourceType.HTTP, "naver"))
        fun itemListFixture() =
            listOf(
                TestItemData(httpAndNaverMetaData, "Alice", 20, "Seoul"),
                TestItemData(httpAndNaverMetaData,"Bob", 30, "Busan"),
                TestItemData(httpAndNaverMetaData,"Charlie", 40, "Daegu"),
                TestItemData(httpAndNaverMetaData,"Kim", 50, "Seoul"),
                TestItemData(httpAndNaverMetaData,"Ally", 30, "Seoul")
            )
    }
}