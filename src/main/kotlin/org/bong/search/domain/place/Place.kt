package org.bong.search.domain.place

data class Places(
    val places: List<Place>
)

data class Place(
    val name: String,
    val address: String
)