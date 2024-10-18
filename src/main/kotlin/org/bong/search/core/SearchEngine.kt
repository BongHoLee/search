package org.bong.search.core

interface SearchEngine<T> {
    fun search(keyword: SearchingKeyword): T
}