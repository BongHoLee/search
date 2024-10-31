package org.bong.search.core.keyword

data class SearchedKeyword<T>(
    val metaData: MetaData,
    val data: T
)


data class MetaData(val source: Source)

data class Source(
    val type: SourceType,
    val from: String,
)

enum class SourceType {
    HTTP
}