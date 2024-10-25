package org.bong.search.core.filter.candidate

import org.bong.search.core.User
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties


abstract class Filter<T : Comparable<T>>(
    open val targetProperty: String,
    open val targetValue: T
){

    inline fun <reified KEYWORD : Any> execute(items: List<KEYWORD>): List<KEYWORD> {
        val property = KEYWORD::class.memberProperties
            .firstOrNull { it.name == targetProperty } as? KProperty1<KEYWORD, T>
            ?: throw IllegalArgumentException("Property $targetProperty not found in ${KEYWORD::class.simpleName}")

        return items.filter { item ->
            val value = property.get(item)
            judge(value)
        }
    }

    abstract fun judge(item: T): Boolean
}

class GraterThanFilter<T : Comparable<T>>(
    override val targetProperty: String,
    override val targetValue: T
) : Filter<T>(targetProperty, targetValue) {
    override fun judge(item: T): Boolean {
        return item > targetValue
    }
}


fun main() {
    val users = listOf(
        User("Alice", 20, "Seoul"),
        User("Bob", 30, "Busan"),
        User("Charlie", 40, "Daegu")
    )

    val filter = GraterThanFilter("age", 30)
    val filteredUsers = filter.execute(users)
    println(filteredUsers)
}