package org.bong.search.core.processor.order.compare

import kotlin.reflect.KProperty1

class CompareByProperty<T, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val next: OrderCompare<T> = NothingCompare(),
) : OrderCompare<T> {
    override fun execute(items: List<T>): List<T> {
        val grouped = items.groupBy { targetProperty.get(it) }
        return grouped.keys
            .sortedByDescending { it }
            .flatMap { next.execute(grouped[it]!!) }
    }
}