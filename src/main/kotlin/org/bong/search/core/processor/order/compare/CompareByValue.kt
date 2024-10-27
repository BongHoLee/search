package org.bong.search.core.processor.order.compare

import org.bong.search.core.processor.operator.ComparisonOperator
import kotlin.reflect.KProperty1

class CompareByValue<T, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val compareValue: V,
    private val comparisonOperator: ComparisonOperator,
) : OrderCompare<T> {
    override fun execute(items: List<T>): List<T> {
        return items.sortedWith(
            compareByDescending { item ->
                comparisonOperator.compare(targetProperty.get(item), compareValue)
            }
        )
    }
}