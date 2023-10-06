package com.nikik0.newsProcessor.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("news")
data class NewsEntity(
    @Id
    val id: Long,
    val info: String,
    val fileHexId: String,
    val createdAt: LocalDateTime
)
