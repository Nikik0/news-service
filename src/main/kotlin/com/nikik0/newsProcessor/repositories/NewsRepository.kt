package com.nikik0.newsProcessor.repositories

import com.nikik0.newsProcessor.entities.NewsEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: CoroutineSortingRepository<NewsEntity, Long>, CoroutineCrudRepository<NewsEntity, Long> {

}