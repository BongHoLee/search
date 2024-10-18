package org.bong.search.common.client

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.reactive.function.client.WebClient

abstract class BaseWebClientApi {

    suspend inline fun <reified T> request(path: String, keyword: String): T {
        return retrieve(path, keyword)
            .bodyToMono(T::class.java)
            .awaitSingle()
    }

    abstract fun retrieve(path: String, keyword: String): WebClient.ResponseSpec
}