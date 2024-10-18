package org.bong.search.common.client.kakao

import org.bong.search.common.client.BaseWebClientApi
import org.springframework.web.reactive.function.client.WebClient

class KakaoWebClientApi(
     private val webClient: WebClient
) : BaseWebClientApi() {

    companion object {
        private const val KAKAO_API_HOST = "dapi.kakao.com"
        private const val KAKAO_API_KEY = "7cf54ca251ed04d1dc0d6f154f113755"
        private const val DOCUMENT_SIZE = 5
    }

    override fun retrieve(path: String, keyword: String): WebClient.ResponseSpec {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(KAKAO_API_HOST)
                    .path(path)
                    .queryParam("query", keyword)
                    .queryParam("size", DOCUMENT_SIZE)
                    .build()
            }
            .header("Authorization", "KakaoAK $KAKAO_API_KEY")
            .retrieve()
    }
}
