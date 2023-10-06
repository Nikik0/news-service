package com.nikik0.newsProcessor.controllers

import com.nikik0.newsProcessor.entities.NewsEntity
import com.nikik0.newsProcessor.repositories.NewsRepository
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/news")
class NewsController(
    private val newsRepository: NewsRepository
) {
    @RequestMapping("/get/{id}")
    suspend fun getSingle(@PathVariable id: Long) =
        newsRepository.findById(id)

    @RequestMapping("/get/all")
    suspend fun getAll() =
        newsRepository.findAll()

    @RequestMapping("/save")
    suspend fun create(@RequestBody news: NewsEntity): NewsEntity {
        news.createdAt = LocalDateTime.now()
        return newsRepository.save(news)
    }

    @RequestMapping("/delete/{id}")
    suspend fun delete(@PathVariable id: Long) =
        newsRepository.deleteById(id)

    @RequestMapping("/get/all/latest")
    suspend fun getAllLatest() =
        newsRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"))
}