package org.bong.search.core

interface SearchService<T> {
    fun search(keyword: SearchingKeyword): T
}
