package org.bong.search.core.processor.filter

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
    private val targetValue: V,
    private val comparisonOperator: ComparisonOperator,
) {
    fun execute(items: List<T>): List<T> {
        return items.filter { item ->
            val value = targetProperty.get(item)
            comparisonOperator.compare(value, targetValue)
        }
    }
}

enum class ComparisonOperator(
    private val symbol: String,
) {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUALS("<=");

    companion object {
        fun from(symbol: String): ComparisonOperator {
            val operator = entries.firstOrNull { it.symbol == symbol }
            if (operator == null) {
                val supportedSymbols = entries.joinToString(", ") { it.symbol }
                throw IllegalArgumentException("Unknown symbol: $symbol. Supported symbols are: $supportedSymbols")
            }
            return operator
        }
    }

    fun <T : Comparable<T>> compare(left: T, right: T): Boolean {
        return when (this) {
            EQUALS -> left == right
            NOT_EQUALS -> left != right
            GREATER_THAN -> left > right
            GREATER_THAN_OR_EQUALS -> left >= right
            LESS_THAN -> left < right
            LESS_THAN_OR_EQUALS -> left <= right
        }
    }
}
