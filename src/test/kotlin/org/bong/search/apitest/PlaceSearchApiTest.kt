package org.bong.search.apitest

import kotlinx.serialization.json.Json
import org.bong.search.client.place.KakaoPlaceResponse
import org.bong.search.client.place.NaverPlaceResponse
import org.junit.jupiter.api.Test
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.client.RestClient

class PlaceSearchApiTest {

    companion object {
        private const val KAKAO_API_HOST = "dapi.kakao.com"
        private const val KAKAO_PLACE_PATH = "/v2/local/search/keyword.json"
        private const val KAKAO_API_KEY = "7cf54ca251ed04d1dc0d6f154f113755"
        private const val DOCUMENT_SIZE = 5

        private const val NAVER_API_HOST = "openapi.naver.com"
        private const val NAVER_PLACE_PATH = "/v1/search/local.json"
        private const val NAVER_CLIENT_ID = "_8o4EDT1hTGUY4iPKU90"
        private const val NAVER_CLIENT_SECRET = "W8zCY43K_R"
        private const val DISPLAY_COUNT = 5
    }

    @Test
    fun `카카오 장소 검색 api test`() {
        val webClient = restClient()
        val keyword = "해수욕장"

        val response = webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(KAKAO_API_HOST)
                    .path(KAKAO_PLACE_PATH)
                    .queryParam("query", keyword)
                    .queryParam("size", DOCUMENT_SIZE)
                    .build()
            }
            .header("Authorization", "KakaoAK $KAKAO_API_KEY")
            .retrieve()
            .body(KakaoPlaceResponse::class.java)

        println(response)
    }

    @Test
    fun `네이버 장소 검색 api test`() {
        val restClient = restClient()
        val keyword = "해수욕장"

        val response = restClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(NAVER_API_HOST)
                    .path(NAVER_PLACE_PATH)
                    .queryParam("query", keyword)
                    .queryParam("display", DISPLAY_COUNT)
                    .build()
            }
            .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
            .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
            .retrieve()
            .body(NaverPlaceResponse::class.java)

        println(response)
    }
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
}


