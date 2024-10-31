package org.bong.search.data.keyword

import org.bong.search.core.keyword.Theme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
class KeywordRepository(
    private val keywordJpaRepository: KeywordJpaRepository
) {
    fun incrementCount(word: String, theme: Theme) {
        keywordJpaRepository.incrementCount(word, theme.name)
    }
}

interface KeywordJpaRepository : JpaRepository<KeywordEntity, Long> {

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

