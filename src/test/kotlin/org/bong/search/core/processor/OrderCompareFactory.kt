package org.bong.search.core.processor

import io.kotest.core.spec.style.FunSpec
import org.bong.search.core.keyword.KeywordMetadata
import org.bong.search.core.keyword.Source
import org.bong.search.core.keyword.SourceType
import org.bong.search.core.processor.operator.ComparisonOperator
import org.bong.search.core.processor.order.compare.CompareByValue
import org.bong.search.core.processor.order.compare.NothingCompare
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


class OrderCriteriaTest : FunSpec({

     test("OrderCriteria의 property를 검증한다") {

         val naverMetaData = KeywordMetadata(Source(SourceType.HTTP, "naver"))
         val kakaoMetaData = KeywordMetadata(Source(SourceType.HTTP, "kakao"))
         val items =  listOf(
             TestItemData(naverMetaData, "Bob", 30, "Busan"),
             TestItemData(kakaoMetaData,"Alice", 20, "Seoul"),
             TestItemData(naverMetaData,"Charlie", 40, "Daegu"),
             TestItemData(kakaoMetaData,"Ally", 30, "Seoul")
         )

        val createCompareByValue = createCompareByValue(
            TestItemData::class,
            "metaData.source.from",
            "naver",
            ComparisonOperator.EQUALS
        )

         val execute = createCompareByValue.execute(items)

         println(execute)
     }
})

fun <T : Any, V : Comparable<V>> createCompareByValue(
    itemClass: KClass<T>,
    propertyPath: String,
    compareValue: V,
    comparisonOperator: ComparisonOperator
): CompareByValue<T, V> {

    // propertyPath를 "."으로 분리하여 중첩된 속성들을 순차적으로 접근
    val properties = propertyPath.split(".")

    // 중첩된 프로퍼티 탐색
    var currentClass: KClass<*> = itemClass
    val propertyGetters = mutableListOf<KProperty1<Any, *>>()

    for (propertyName in properties) {
        val property = currentClass.memberProperties
            .find { it.name == propertyName } ?: throw IllegalArgumentException("Property not found.")

        property.isAccessible = true
        propertyGetters.add(property as KProperty1<Any, *>)

        // KClass 타입으로 캐스팅 -> 원래 classifier는 클래스(KClass)일수도 있고 KTypeParameter일수도 있음
        currentClass = property.returnType.classifier as? KClass<*> ?: throw IllegalArgumentException("Property type not found.")
    }

    // 최종적으로 반환된 프로퍼티의 타입이 compareValue와 일치하는지 확인
    if (!propertyGetters.last().returnType.isSubtypeOf(compareValue::class.createType())) {
        throw IllegalArgumentException("Property type does not match the type of compareValue.")
    }


    // selector를 만들어 CompareByValue 생성
    val selector: (T) -> V = { item ->
        var currentItem: Any = item
        for (getter in propertyGetters) {
            currentItem = getter.get(currentItem)!!
        }

        currentItem as V
    }
    return CompareByValue(compareValue, comparisonOperator, NothingCompare(), selector)
}
