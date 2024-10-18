package org.bong.search.domain.place.infra.client

import kotlinx.serialization.Serializable
import org.bong.search.domain.place.service.Place
import org.bong.search.domain.place.service.Places

@Serializable
data class NaverPlaceResponse(
    val items: List<Item>
) {
    fun toPlaces() = Places(
        places = items.map {
            Place(
                name = it.title,
                address = it.roadAddress
            )
        }
    )
}

@Serializable
data class Item(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String
)


