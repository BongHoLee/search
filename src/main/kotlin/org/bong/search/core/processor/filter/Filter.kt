package org.bong.search.core.processor.filter

import org.bong.search.core.processor.operator.ComparisonOperator
import kotlin.reflect.KProperty1


class Filter<T : Any>(
    private val filterOperand: FilterOperand<T>,
) {
    fun execute(items: List<T>): List<T> {
        return filterOperand.execute(items.toSet()).toList()
    }
}

interface FilterOperand<T : Any> {
    fun execute(items: Set<T>): Set<T>
}

class OrOperand<T : Any>(
    private val left: FilterOperand<T>,
    private val right: FilterOperand<T>,
) : FilterOperand<T> {

    override fun execute(items: Set<T>): Set<T> {
        return left.execute(items).union(right.execute(items))
    }
}

class AndOperand<T : Any>(
    private val left: FilterOperand<T>,
    private val right: FilterOperand<T>,
) : FilterOperand<T> {
    override fun execute(items: Set<T>): Set<T> {
        return left.execute(items).intersect(right.execute(items))
    }
}

class LeafOperand<T : Any, V : Comparable<V>>(
    private val targetProperty: KProperty1<T, V>,
    private val compareValue: V,
    private val comparisonOperator: ComparisonOperator,
) : FilterOperand<T> {

    override fun execute(items: Set<T>): Set<T> {
        return items.filter { item ->
            val originValue = targetProperty.get(item)
            comparisonOperator.compare(originValue, compareValue)
        }.toSet()
    }
}
