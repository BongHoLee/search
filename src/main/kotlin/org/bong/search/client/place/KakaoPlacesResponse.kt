package org.bong.search.client.place

import kotlinx.serialization.Serializable
import org.bong.search.domain.place.Place
import org.bong.search.domain.place.Places

@Serializable
data class KakaoPlaceResponse(
    val documents: List<Document>
) {
    fun toPlaces() = Places(
        places = documents.map {
            Place(
                name = it.place_name,
                address = it.road_address_name
            )
        }
    )
}


@Serializable
data class Document(
    val place_name: String,
    val distance: String,
    val place_url: String,
    val category_name: String,
    val address_name: String,
    val road_address_name: String,
    val id: String,
    val phone: String,
    val category_group_code: String?,
    val category_group_name: String?,
    val x: String,
    val y: String
)