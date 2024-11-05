package org.bong.search.core.keyword


interface SearchedKeyword {
    val metaData: KeywordMetadata
}

data class KeywordMetadata(val source: Source)

data class Source(
    val type: SourceType,
    val from: String,
)

enum class SourceType {
    HTTP
}