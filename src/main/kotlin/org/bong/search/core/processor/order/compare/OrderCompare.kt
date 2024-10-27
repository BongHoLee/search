package org.bong.search.core.processor.order.compare

interface OrderCompare<T> {
    fun execute(items: List<T>): List<T>
}