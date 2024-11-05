package org.bong.search.core.processor.order

import org.bong.search.core.keyword.KeywordMetadata
import org.bong.search.core.keyword.Source
import org.bong.search.core.keyword.SourceType
import org.bong.search.core.processor.TestItemData
import org.bong.search.core.processor.createCompareByValue
import org.bong.search.core.processor.operator.ComparisonOperator
import org.bong.search.core.processor.order.compare.CompareByValue
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
open class CompareByValueBenchMark {
    // 100,000개의 TestItemData 객체 생성
    private val naverMetaData = KeywordMetadata(Source(SourceType.HTTP, "naver"))
    private val kakaoMetaData = KeywordMetadata(Source(SourceType.HTTP, "kakao"))
    private val items = List(10_000) { index ->
        if (index % 2 == 0) {
            TestItemData(naverMetaData, "Bob$index", 30, "Busan")
        } else {
            TestItemData(kakaoMetaData, "Alice$index", 20, "Seoul")
        }
    }

    // 리플렉션을 사용하지 않는 버전
    @Benchmark
    fun nonReflectionBenchmark(): List<TestItemData> {
        val fromOrdering = "kakao"
        val comparator = CompareByValue<TestItemData, String>(
            fromOrdering,
            ComparisonOperator.CONTAINS
        ) { it.metaData.source.from }

        return comparator.execute(items)
    }

    // 리플렉션을 사용하는 버전
    @Benchmark
    fun reflectionBenchmark(): List<TestItemData> {
        val comparator = createCompareByValue(
            TestItemData::class,
            "metaData.source.from",
            "kakao",
            ComparisonOperator.CONTAINS
        )

        return comparator.execute(items)
    }

}