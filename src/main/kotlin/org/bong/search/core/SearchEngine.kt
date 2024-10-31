package org.bong.search.core

import org.bong.search.core.keyword.SearchingKeyword

interface SearchEngine<T> {
    fun search(keyword: SearchingKeyword): T
}