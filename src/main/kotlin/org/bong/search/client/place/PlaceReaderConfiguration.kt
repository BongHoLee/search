package org.bong.search.client.place

import org.bong.search.client.api.kakao.KakaoWebClientApi
import org.bong.search.client.api.naver.NaverWebClientApi
import org.bong.search.domain.place.PlaceReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlaceReaderConfiguration {

    @Bean
    fun placeReader(kakaoWebClientApi: KakaoWebClientApi, naverWebClientApi: NaverWebClientApi): PlaceReader =
        PlaceSearchClientApi(kakaoWebClientApi, naverWebClientApi)
}