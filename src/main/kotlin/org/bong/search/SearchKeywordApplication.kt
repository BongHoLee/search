package org.bong.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class SearchKeywordApplication

fun main(args: Array<String>) {
    runApplication<SearchKeywordApplication>(*args)
}
