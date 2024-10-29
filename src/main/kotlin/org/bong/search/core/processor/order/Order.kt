package org.bong.search.core.processor.order

import org.bong.search.core.processor.order.compare.OrderCompare

class Order<T> (
    private val orderCompare: OrderCompare<T>
) {
    fun execute(items: List<T>): List<T> {
        return orderCompare.execute(items)
    }
}