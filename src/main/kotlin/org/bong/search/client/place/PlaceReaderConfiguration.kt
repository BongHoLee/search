package org.bong.search.client.place

import org.bong.search.client.api.kakao.KakaoClientApi
import org.bong.search.client.api.naver.NaverClientApi
import org.bong.search.domain.place.PlaceReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlaceReaderConfiguration {

    @Bean
    fun placeReader(kakaoClientApi: KakaoClientApi, naverClientApi: NaverClientApi): PlaceReader =
        PlaceSearchClientApi(kakaoClientApi, naverClientApi)
}