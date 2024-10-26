package org.bong.search.core.processor.filter

import org.bong.search.core.processor.operator.ComparisonOperator
import kotlin.reflect.KProperty1


class Filter<T : Any>(
    private val filters: List<FilterStrategy<T, *>>,
) {
    fun execute(items: List<T>): List<T> {
        var filteredItems = items
        filters.forEach { filter ->
            filteredItems = filter.execute(filteredItems)
        }
        return filteredItems
    }
}

class FilterStrategy<T : Any, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val compareValue: V,
    private val comparisonOperator: ComparisonOperator,
) {
    fun execute(items: List<T>): List<T> {
        return items.filter { item ->
            val originValue = targetProperty.get(item)
            comparisonOperator.compare(originValue, compareValue)
        }
    }
}
