package com.nikik0.newsProcessor.dtos

import com.nikik0.newsProcessor.entities.NewsEntity
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

//fun NewsSubmitDto.toEntity() =
//    NewsEntity(
//        id = this.id,
//        title = this.title,
//        info = this.info,
//        fileHexId = this.fileHexId,
//        createdAt = LocalDateTime.now()
//    )

data class NewsSubmitDto(
    val id: Long,
    val title: String,
    val info: String,
    val fileHexId: String
){
    fun toEntity() =
        NewsEntity(
            id = this.id,
            title = this.title,
            info = this.info,
            fileHexId = this.fileHexId,
            createdAt = LocalDateTime.now()
        )
}
