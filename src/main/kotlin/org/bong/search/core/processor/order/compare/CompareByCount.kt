package org.bong.search.core.processor.order.compare

import kotlin.reflect.KProperty1

class CompareByCount<T, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val next: OrderCompare<T>
)  {
    fun execute(items: List<T>): List<T> {
        val orderingByCount = orderingByCount(items)

        return orderingByCount.flatMap {
            next.execute(it)
        }
    }

    private fun orderingByCount(items: List<T>): List<List<T>> {
        val orderingByCount = items.groupBy { targetProperty.get(it) }
            .values
            .groupBy { it.size }
            .values
            .map { groupedLists ->
                groupedLists.map { it.first() }
            }
        return orderingByCount
    }
}