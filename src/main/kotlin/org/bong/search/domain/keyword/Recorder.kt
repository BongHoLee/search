package org.bong.search.domain.keyword

import org.bong.search.core.SearchingKeyword

interface Recorder {
    fun record(keyword: SearchingKeyword)
}