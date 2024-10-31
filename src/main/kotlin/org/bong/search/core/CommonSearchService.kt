package org.bong.search.core

import org.bong.search.core.keyword.SearchingKeyword
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