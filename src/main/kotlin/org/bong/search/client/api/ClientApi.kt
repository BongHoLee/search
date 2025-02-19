package org.bong.search.client.api

import org.springframework.web.client.RestClient

abstract class BaseClientApi {

    inline fun <reified T> request(path: String, keyword: String): T {
        return retrieve(path, keyword)
            .body(T::class.java)!!
    }

    abstract fun retrieve(path: String, keyword: String): RestClient.ResponseSpec
}