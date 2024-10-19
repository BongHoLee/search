package org.bong.search.data.keyword

import org.bong.search.core.SearchingKeyword
import org.bong.search.domain.keyword.Recorder
import org.springframework.stereotype.Component

@Component
class KeywordRecording(
    private val keywordRepository: KeywordRepository
) : Recorder {

    override fun record(keyword: SearchingKeyword) {
        keywordRepository.incrementCount(keyword.keyword, keyword.theme)
    }
}