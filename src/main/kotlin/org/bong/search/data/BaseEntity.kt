package org.bong.search.data

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @PrePersist
    fun onCreate() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}