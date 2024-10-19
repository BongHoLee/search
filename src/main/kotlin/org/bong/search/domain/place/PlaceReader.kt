package org.bong.search.domain.place

import org.bong.search.core.SearchingKeyword

interface PlaceReader {
    fun read(searchingKeyword: SearchingKeyword): Places
}