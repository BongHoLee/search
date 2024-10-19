package org.bong.search.domain.place

import org.bong.search.client.api.kakao.KakaoWebClientApi
import org.bong.search.client.api.naver.NaverWebClientApi
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
    fun recordKeyword(asyncRecordKeyword: AsyncRecordKeyword): RecordKeyword =
        RecordKeyword(asyncRecordKeyword)

    @Bean
    fun placeSearch(placeSearchClientApi: PlaceSearchClientApi): PlaceSearch =
        PlaceSearch(placeSearchClientApi)


    @Bean
    fun placeSearchClientApi(kakaoWebClientApi: KakaoWebClientApi, naverWebClientApi: NaverWebClientApi): PlaceSearchClientApi =
        PlaceSearchClientApi(kakaoWebClientApi, naverWebClientApi)


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


