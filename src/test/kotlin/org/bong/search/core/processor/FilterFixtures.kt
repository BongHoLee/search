package org.bong.search.core.processor

data class TestItemData(
    val name: String,
    val age: Int,
    val address: String
) {
    companion object {
        fun itemListFixture() =
            listOf(
                TestItemData("Alice", 20, "Seoul"),
                TestItemData("Bob", 30, "Busan"),
                TestItemData("Charlie", 40, "Daegu"),
                TestItemData("Kim", 50, "Seoul"),
                TestItemData("Ally", 30, "Seoul")
            )
    }
}