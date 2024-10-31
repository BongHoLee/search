package org.bong.search.core.keyword

data class SearchingKeyword(
    val keyword: String,
    val theme: Theme
) {
    init {
        require(keyword.isNotEmpty()) { "keyword must not be empty" }
    }
}

enum class Theme {
    PLACE
}
