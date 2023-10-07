package com.nikik0.newsProcessor.dtos

import com.nikik0.newsProcessor.entities.NewsEntity
import com.nikik0.newsProcessor.remote.FileService
import java.time.LocalDateTime

data class NewsSubmitFileDto(
    val id: Long,
    val title: String,
    val info: String
)
