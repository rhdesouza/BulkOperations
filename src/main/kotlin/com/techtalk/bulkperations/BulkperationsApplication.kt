package com.techtalk.bulkperations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
@EnableMongoRepositories("com.techtalk.bulkperations.repository.MongoRepository")
class BulkperationsApplication

fun main(args: Array<String>) {
	val runApplication = runApplication<BulkperationsApplication>(*args)
}
