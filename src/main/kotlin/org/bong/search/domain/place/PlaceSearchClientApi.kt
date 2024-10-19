package org.bong.search.domain.place

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.bong.search.client.api.kakao.KakaoWebClientApi
import org.bong.search.client.api.naver.NaverWebClientApi
import org.bong.search.client.place.KakaoPlaceResponse
import org.bong.search.client.place.NaverPlaceResponse
import org.bong.search.core.SearchingKeyword

