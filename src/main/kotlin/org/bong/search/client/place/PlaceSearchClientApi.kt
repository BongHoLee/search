package org.bong.search.client.place

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.bong.search.client.api.kakao.KakaoClientApi
import org.bong.search.client.api.naver.NaverClientApi
import org.bong.search.core.keyword.SearchingKeyword
import org.bong.search.domain.place.PlaceReader
import org.bong.search.domain.place.Places

class PlaceSearchClientApi(
    private val kakaoClientApi: KakaoClientApi,
    private val naverClientApi: NaverClientApi
) : PlaceReader {

    companion object {
        private const val KAKAO_PLACE_PATH = "/v2/local/search/keyword.json"
        private const val NAVER_PLACE_PATH = "/v1/search/local.json"
    }

    override fun read(searchingKeyword: SearchingKeyword): Places = runBlocking {
        coroutineScope {
            val kakaoPlacesDeferred = async { requestToKakaoApi(searchingKeyword.keyword) }
            val naverPlacesDeferred = async { requestToNaverApi(searchingKeyword.keyword) }

            combinePlaces(kakaoPlacesDeferred.await(), naverPlacesDeferred.await())
        }
    }
    private fun requestToKakaoApi(keyword: String): Places =
        kakaoClientApi.request<KakaoPlaceResponse>(keyword = keyword, path = KAKAO_PLACE_PATH).toPlaces()

    private fun requestToNaverApi(keyword: String): Places =
        naverClientApi.request<NaverPlaceResponse>(keyword = keyword, path = NAVER_PLACE_PATH).toPlaces()

    private fun combinePlaces(kakaoPlaces: Places, naverPlaces: Places): Places {
        return Places(
            places = kakaoPlaces.places + naverPlaces.places
        )
    }
}