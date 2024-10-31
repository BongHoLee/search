package org.bong.search.domain.place

import org.bong.search.core.SearchEngine
import org.bong.search.core.keyword.SearchingKeyword


class PlaceSearch(
    private val placeReader: PlaceReader
) : SearchEngine<Places> {

    override fun search(keyword: SearchingKeyword): Places {
        return placeReader.read(keyword)
    }
}
