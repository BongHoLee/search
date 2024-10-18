package org.bong.search.domain.place.service

import org.bong.search.core.SearchEngine
import org.bong.search.core.SearchingKeyword
import org.bong.search.domain.place.infra.client.PlaceSearchClientApi


class PlaceSearch(
    private val placeSearchClientApi: PlaceSearchClientApi
) : SearchEngine<Places> {

    override fun search(keyword: SearchingKeyword): Places {
        return placeSearchClientApi.search(keyword)
    }
}
