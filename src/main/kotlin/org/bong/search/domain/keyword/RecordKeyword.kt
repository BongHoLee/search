package org.bong.search.domain.keyword

import org.bong.search.core.SearchingKeyword
import org.bong.search.core.processor.PreProcessor
import org.bong.search.data.keyword.KeywordJpaRepository
import org.springframework.scheduling.annotation.Async


class RecordKeyword(
    private val recordKeyword: AsyncRecordKeyword
) : PreProcessor {
    override fun process(keyword: SearchingKeyword): SearchingKeyword {
        recordKeyword.record(keyword)
        return keyword
    }
}

open class AsyncRecordKeyword(
    private val recorder: Recorder
) {
    @Async
    open fun record(keyword: SearchingKeyword) {
        recorder.record(keyword)
    }
}