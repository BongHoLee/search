package org.bong.search.domain.place

import org.bong.search.common.client.kakao.KakaoWebClientApi
import org.bong.search.common.client.naver.NaverWebClientApi
import org.bong.search.common.service.CommonSearchService
import org.bong.search.core.processor.PostProcessor
import org.bong.search.domain.keyword.infra.data.KeywordRepository
import org.bong.search.domain.keyword.service.AsyncRecordKeyword
import org.bong.search.domain.keyword.service.RecordKeyword
import org.bong.search.domain.place.infra.client.PlaceSearchClientApi
import org.bong.search.domain.place.service.PlaceSearch
import org.bong.search.domain.place.service.Places
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


