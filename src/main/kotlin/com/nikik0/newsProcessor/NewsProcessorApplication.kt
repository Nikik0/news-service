package com.nikik0.newsProcessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsProcessorApplication

fun main(args: Array<String>) {
	runApplication<NewsProcessorApplication>(*args)
}
