package com.nikik0.newsProcessor.remote

import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.io.File
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class FileService {

    private val fileUrl: String = "http://localhost:8080/api/v1/storage/"

    private val webClient = WebClient.builder().baseUrl(fileUrl).build()

    suspend fun getFile(hexId: String) =
        webClient.method(HttpMethod.GET).uri("get/$hexId")
            .retrieve().bodyToMono(kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object::class.java)

    suspend fun saveFile(file: MultipartFile){
        val ee  = MultipartBodyBuilder()

        ee.part("file", ByteArrayResource(file.bytes)).filename(file.name)
        ee.build()
        val builder = MultipartBodyBuilder().part("file", ByteArrayResource(file.bytes)).filename(file.name)

        println(file.bytes)
        println(file.size)
        val s = ByteArrayResource(file.bytes)
        println(s.filename)
        println(file.name)
        webClient.post()
            .uri("/save")
            .contentLength(file.size)
            .header(HttpHeaders.HOST, "newsMS")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
            .body(BodyInserters.fromMultipartData("file", ee.build()))
            .retrieve()
            .bodyToMono<JvmType.Object>().subscribe()
    }

    suspend fun saveFile2(file: File){
        val ee  = MultipartBodyBuilder()

        ee.part("file", FileSystemResource(file))
        //ee.build()
        //val builder = MultipartBodyBuilder().part("file", ByteArrayResource(file.bytes)).filename(file.name)
//
//        println(file.bytes)
////        println(file.size)
//        val s = ByteArrayResource(file.bytes)
//        println(s.filename)
//        println(file.name)
        webClient.post()
            .uri("/save")
            .contentLength(file.length())
            .header(HttpHeaders.HOST, "newsMS")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
            .body(BodyInserters.fromMultipartData("file", ee.build()))
            .retrieve()
            .bodyToMono<JvmType.Object>().subscribe()
    }
}