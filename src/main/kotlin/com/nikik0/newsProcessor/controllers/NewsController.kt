package com.nikik0.newsProcessor.controllers

import com.nikik0.newsProcessor.dtos.NewsSubmitDto
import com.nikik0.newsProcessor.entities.NewsEntity
import com.nikik0.newsProcessor.remote.FileService
import com.nikik0.newsProcessor.repositories.NewsRepository
import com.nikik0.newsProcessor.services.NewsService
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files

@RestController
@RequestMapping("/api/v1/news")
class NewsController(
    private val newsService: NewsService,
    private val fileService: FileService
) {
    @GetMapping("/get/{id}")
    suspend fun getSingle(@PathVariable id: Long) =
        newsService.getSingle(id)

    @GetMapping("/get/all")
    suspend fun getAll() =
        newsService.getAll()

    @PostMapping("/save")
    suspend fun create(@RequestBody news: NewsSubmitDto, @RequestHeader("Authorization") auth: String) =
        newsService.create(news, auth)

    //todo doesn't work for now, smh keep getting fileupload.MultipartStream$MalformedStreamException:
    // Stream ended unexpectedly in fileStorge,
    // need to investigate later
    @PostMapping("/saveFile", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun createFile(
//        @RequestParam("title") title: String,
//        @RequestParam("info") info: String,
        @RequestParam("file") file: MultipartFile, @RequestHeader("Authorization") auth: String
    )//NewsEntity {
    {
        println(file.bytes)
        //fileService.saveFile(file)
        //val hexFileId = fileService.saveFile(file)
        val sm = file.inputStream
        val fii = Files.createTempFile(null,null)
        Files.write(fii,file.bytes)
        fileService.saveFile2(fii.toFile())
//        val s = hexFileId.asFlow()
//        s.onEach { println(it) }.collect()
//        println(hexFileId)

        //return newsRepository.save(news.toEntity())
    }

    @PostMapping("/delete/{id}")
    suspend fun delete(@PathVariable id: Long, @RequestHeader("Authorization") auth: String) =
        newsService.delete(id, auth)

    @GetMapping("/get/all/latest")
    suspend fun getAllLatest() =
        newsService.getAllLatest()

}