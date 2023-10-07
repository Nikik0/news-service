package com.nikik0.newsProcessor.dtos

data class NewsResponseDto(
    val id: Long,
    val title: String,
    val info: String,
    val fileHexId: String
)
