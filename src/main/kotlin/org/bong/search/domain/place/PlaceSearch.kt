package org.bong.search.domain.place

import org.bong.search.core.SearchEngine
import org.bong.search.core.SearchingKeyword


class PlaceSearch(
    private val placeSearchClientApi: PlaceSearchClientApi
) : SearchEngine<Places> {

    override fun search(keyword: SearchingKeyword): Places {
        return placeSearchClientApi.search(keyword)
    }
}
