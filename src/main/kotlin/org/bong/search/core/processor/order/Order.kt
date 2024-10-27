package org.bong.search.core.processor.order

import org.bong.search.core.processor.order.compare.OrderCompare

class Order<T> (
    private val compares: List<OrderCompare<T>>
) {
    fun execute(items: List<T>): List<T> {
        var orderedItems = items
        compares.forEach { compare ->
            orderedItems = compare.execute(orderedItems)
        }
        return orderedItems
    }
}