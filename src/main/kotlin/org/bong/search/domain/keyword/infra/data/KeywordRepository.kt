package org.bong.search.domain.keyword.infra.data

import org.bong.search.core.Theme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface KeywordRepository : JpaRepository<KeywordEntity, Long> {

    fun findByWordAndTheme(word: String, theme: Theme): KeywordEntity?

    @Modifying
    @Transactional
    @Query(
        value = """
            INSERT INTO searched_keywords (word, theme, count, created_at, updated_at)
                VALUES (:word, :themeValue, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            ON DUPLICATE KEY UPDATE
                count = count + 1,
                updated_at = CURRENT_TIMESTAMP
            
                """,
        nativeQuery = true
    )
    fun incrementCount(@Param("word") word: String, @Param("themeValue") themeValue: String)
}
