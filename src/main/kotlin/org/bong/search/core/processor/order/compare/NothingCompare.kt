package org.bong.search.core.processor.order.compare

class NothingCompare<T> : OrderCompare<T>{
    override fun execute(items: List<T>): List<T> {
        return items
    }
}

