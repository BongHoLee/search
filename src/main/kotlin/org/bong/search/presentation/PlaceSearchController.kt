package org.bong.search.presentation

import org.bong.search.core.SearchService
import org.bong.search.core.keyword.SearchingKeyword
import org.bong.search.core.keyword.Theme
import org.bong.search.domain.place.Places
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class PlaceSearchController(private val placeSearchService: SearchService<Places>) {

    @GetMapping("/search/place")
    fun searchPlaces(@RequestParam keyword: String): Places {

        return placeSearchService.search(
            SearchingKeyword(
                keyword = keyword,
                theme = Theme.PLACE
            )
        )
    }
}