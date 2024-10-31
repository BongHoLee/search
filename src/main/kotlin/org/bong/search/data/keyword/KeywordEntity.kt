package org.bong.search.data.keyword

import jakarta.persistence.*
import org.bong.search.data.BaseEntity
import org.bong.search.core.keyword.SearchingKeyword
import org.bong.search.core.keyword.Theme


@Entity
@Table(
    name = "searched_keywords",
    uniqueConstraints = [UniqueConstraint(columnNames = ["word", "theme"])]
)
class KeywordEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "word", nullable = false)
    val word: String,

    @Enumerated(EnumType.STRING)
    val theme: Theme,

    val count: Long = 0L
) : BaseEntity() {
    fun toSearchingKeyword(): SearchingKeyword = SearchingKeyword(word, theme)
}