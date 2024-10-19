package org.bong.search.domain.place

import org.bong.search.core.CommonSearchService
import org.bong.search.core.processor.PostProcessor
import org.bong.search.data.keyword.KeywordRepository
import org.bong.search.domain.keyword.AsyncRecordKeyword
import org.bong.search.domain.keyword.RecordKeyword
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlaceSearchUseCaseConfiguration {

    @Bean
    fun placeSearchService(
        recordKeyword: RecordKeyword,
        placeSearch: PlaceSearch,
        placeSearchMockPostProcessor: PostProcessor,
    ): CommonSearchService<Places> =
        CommonSearchService(
            preProcessor = recordKeyword,
            searchEngine = placeSearch,
            postProcessor = placeSearchMockPostProcessor
        )

    @Bean
    fun placeSearch(placeReader: PlaceReader): PlaceSearch =
        PlaceSearch(placeReader)

    @Bean
    fun recordKeyword(asyncRecordKeyword: AsyncRecordKeyword): RecordKeyword =
        RecordKeyword(asyncRecordKeyword)

    @Bean
    fun asyncRecordKeyword(keywordRepository: KeywordRepository): AsyncRecordKeyword =
        AsyncRecordKeyword(keywordRepository)

    // create PostProcessor bean
    @Bean
    fun placeSearchMockPostProcessor(): PostProcessor {
        return object : PostProcessor {
            override fun <T> process(result: T): T {
                return result
            }
        }
    }

}


