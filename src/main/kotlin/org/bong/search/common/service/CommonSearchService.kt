package org.bong.search.common.service

import org.bong.search.core.SearchEngine
import org.bong.search.core.SearchService
import org.bong.search.core.SearchingKeyword
import org.bong.search.core.processor.PostProcessor
import org.bong.search.core.processor.PreProcessor

class CommonSearchService<T>(
    private val preProcessor: PreProcessor,
    private val searchEngine: SearchEngine<T>,
    private val postProcessor: PostProcessor
) : SearchService<T> {

    override fun search(keyword: SearchingKeyword): T {
        val preProcessedKeyword = preProcessor.process(keyword)
        val search = searchEngine.search(preProcessedKeyword)
        return postProcessor.process(search)
    }
}