package com.nikik0.newsProcessor.controllers

import com.nikik0.newsProcessor.repositories.NewsRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


}