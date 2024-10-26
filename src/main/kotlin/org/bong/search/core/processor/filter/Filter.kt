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

enum class ComparisonOperator(
    private val symbol: String,
) {
    EQUALS("EQ"),
    NOT_EQUALS("NEQ"),
    GREATER_THAN("GT"),
    GREATER_THAN_OR_EQUALS("GTE"),
    LESS_THAN("LT"),
    LESS_THAN_OR_EQUALS("LTE"),
    CONTAINS("CTN");

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
            CONTAINS -> {
                if (left is String && right is String) {
                    left.contains(right)
                } else {
                    throw IllegalArgumentException("Only String type is supported for CONTAINS operator")
                }
            }
        }
    }
}
