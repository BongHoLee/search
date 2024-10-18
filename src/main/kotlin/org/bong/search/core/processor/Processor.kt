package org.bong.search.core.processor

import org.bong.search.core.SearchingKeyword

interface PreProcessor {
    fun process(keyword: SearchingKeyword): SearchingKeyword
}

interface PostProcessor {
    fun <T> process(target: T): T
}