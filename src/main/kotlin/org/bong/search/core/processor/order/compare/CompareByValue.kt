package org.bong.search.core.processor.order.compare

import org.bong.search.core.processor.operator.ComparisonOperator
import kotlin.reflect.KProperty1

class CompareByValue<T, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val compareValue: V,
    private val comparisonOperator: ComparisonOperator,
    private val next: OrderCompare<T> = NothingCompare()
) : OrderCompare<T> {
    override fun execute(items: List<T>): List<T> {
        val (matched, notMatched) = groupingMatchedOrNot(items)

        return listOf(matched, notMatched)
            .flatMap { next.execute(it) }
    }

    private fun groupingMatchedOrNot(items: List<T>): Pair<List<T>, List<T>> {
        return items.partition { item ->
            comparisonOperator.compare(targetProperty.get(item), compareValue)
        }
    }
}