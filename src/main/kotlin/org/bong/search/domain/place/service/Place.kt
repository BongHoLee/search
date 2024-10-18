package org.bong.search.domain.place.service

data class Places(
    val places: List<Place>
)

data class Place(
    val name: String,
    val address: String
)