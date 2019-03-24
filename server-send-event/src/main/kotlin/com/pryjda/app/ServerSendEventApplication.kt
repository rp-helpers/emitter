package com.pryjda.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerSendEventApplication

fun main(args: Array<String>) {
    runApplication<ServerSendEventApplication>(*args)
}
