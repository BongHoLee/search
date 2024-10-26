package org.bong.search.core.processor.operator

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