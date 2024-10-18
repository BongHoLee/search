package org.bong.search.domain.keyword.infra.data

import jakarta.persistence.*
import org.bong.search.common.data.BaseEntity
import org.bong.search.core.SearchingKeyword
import org.bong.search.core.Theme


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