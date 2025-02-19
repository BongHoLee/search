package org.bong.search.client.api

import kotlinx.serialization.json.Json
import org.bong.search.client.api.kakao.KakaoClientApi
import org.bong.search.client.api.naver.NaverClientApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {

    @Bean
    fun restClient(): RestClient {
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.setConnectTimeout(5000)
        requestFactory.setConnectionRequestTimeout(5000)

        return RestClient.builder()
            .requestFactory(requestFactory)
            .messageConverters { converters ->
                converters.removeIf { it is KotlinSerializationJsonHttpMessageConverter }
                converters.add(KotlinSerializationJsonHttpMessageConverter(Json { ignoreUnknownKeys = true }))
            }
            .build()
    }

    @Bean
    fun kakaoWebClientApi(restClient: RestClient): KakaoClientApi {
        return KakaoClientApi(restClient)
    }

    @Bean
    fun naverWebClientApi(restClient: RestClient): NaverClientApi {
        return NaverClientApi(restClient)
    }
}