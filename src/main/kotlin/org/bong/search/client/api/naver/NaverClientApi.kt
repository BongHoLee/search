package org.bong.search.client.api.naver

import org.bong.search.client.api.BaseClientApi
import org.springframework.web.client.RestClient

class NaverClientApi(
     private val restClient: RestClient
) : BaseClientApi() {

    companion object {
        private const val NAVER_API_HOST = "openapi.naver.com"
        private const val NAVER_CLIENT_ID = "_8o4EDT1hTGUY4iPKU90"
        private const val NAVER_CLIENT_SECRET = "W8zCY43K_R"
        private const val DISPLAY_COUNT = 5
    }

    override fun retrieve(path: String, keyword: String): RestClient.ResponseSpec {
        return restClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(NAVER_API_HOST)
                    .path(path)
                    .queryParam("query", keyword)
                    .queryParam("display", DISPLAY_COUNT)
                    .build()
            }
            .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
            .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
            .retrieve()
    }
}
