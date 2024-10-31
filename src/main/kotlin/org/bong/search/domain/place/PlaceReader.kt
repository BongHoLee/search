package org.bong.search.domain.place

import org.bong.search.core.keyword.SearchingKeyword

interface PlaceReader {
    fun read(searchingKeyword: SearchingKeyword): Places
}