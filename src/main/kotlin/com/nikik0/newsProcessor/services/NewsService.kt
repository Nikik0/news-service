package com.nikik0.newsProcessor.services

import com.nikik0.newsProcessor.dtos.CurrentUserInfo
import com.nikik0.newsProcessor.dtos.NewsSubmitDto
import com.nikik0.newsProcessor.entities.NewsEntity
import com.nikik0.newsProcessor.repositories.NewsRepository
import kotlinx.coroutines.flow.toList
import org.springframework.data.domain.Sort
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow

@Service
class NewsService(
    private val newsRepository: NewsRepository
) {

    private val baseurl = "http://localhost:8080/api/v1/auth/info"

    private val securityProvider = WebClient.builder().baseUrl(baseurl).build()

    private suspend fun callToCheckCred(authentication: String, acceptedRoles: List<String>): Boolean {
        val currentUserInfo =
            securityProvider.get().header(HttpHeaders.AUTHORIZATION, authentication).retrieve().bodyToFlow<CurrentUserInfo>()
                .toList().first()
        return acceptedRoles.contains(currentUserInfo.role.lowercase())
    }

    suspend fun create(newsSubmitDto: NewsSubmitDto, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            newsRepository.save(newsSubmitDto.toEntity())
        else null

    suspend fun delete(id: Long, auth: String) =
        if (callToCheckCred(auth, listOf("mentor", "admin")))
            newsRepository.deleteById(id)
        else null

    suspend fun getSingle(id: Long) =
        //if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            newsRepository.findById(id)
        //else null

    suspend fun getAll() =
        //if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            newsRepository.findAll()
        //else null

    suspend fun update(newsEntity: NewsEntity, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            newsRepository.save(newsEntity)
        else null

    suspend fun getAllLatest() =
        newsRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"))
}