package com.nikik0.newsProcessor.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("news")
data class NewsEntity(
    @Id
    val id: Long,
    val title: String,
    val info: String,
    @Column("file_hex_id")
    val fileHexId: String,
    @Column("created_at")
    val createdAt: LocalDateTime
)
