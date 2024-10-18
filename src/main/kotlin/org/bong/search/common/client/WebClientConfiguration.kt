package org.bong.search.common.client

import kotlinx.serialization.json.Json
import org.bong.search.common.client.kakao.KakaoWebClientApi
import org.bong.search.common.client.naver.NaverWebClientApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration {

    @Bean
    fun webClient(): WebClient {
        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }

        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(json))
                configurer.defaultCodecs().kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(json))
            }.build()

        return WebClient.builder()
            .exchangeStrategies(exchangeStrategies)
            .build()
    }


    @Bean
    fun kakaoWebClientApi(webClient: WebClient): KakaoWebClientApi {
        return KakaoWebClientApi(webClient)
    }

    @Bean
    fun naverWebClientApi(webClient: WebClient): NaverWebClientApi {
        return NaverWebClientApi(webClient)
    }

}