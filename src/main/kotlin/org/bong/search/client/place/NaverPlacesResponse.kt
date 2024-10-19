package org.bong.search.client.place

import kotlinx.serialization.Serializable
import org.bong.search.domain.place.Place
import org.bong.search.domain.place.Places

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


