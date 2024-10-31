package org.bong.search.domain.keyword

import org.bong.search.core.keyword.SearchingKeyword

interface Recorder {
    fun record(keyword: SearchingKeyword)
}