package com.pryjda.app.app1.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.ForkJoinPool
import java.util.logging.Logger
import kotlin.random.Random

@RestController
class MessageController {

    companion object {
        val LOGGER: Logger = Logger.getLogger(MessageController::class.toString())
    }

    val messages: MutableList<String> = mutableListOf("one", "two", "three", "four", "five")

    @GetMapping("/messages")
    fun getMyMessages(): MutableList<String> {
        LOGGER.info("GET / messages")
        return messages
    }

    @GetMapping("/blocking-request")
    fun blockHttpRequest(): ResponseEntity<Any> {
        LOGGER.info("GET / blocking-request")
        Thread.sleep(10000)
        var randomNumber = Random.nextInt(1000)
        LOGGER.info("before return response entity")
        return ResponseEntity.ok(randomNumber)
    }

    @GetMapping("/asynchronous-request")
    fun asynchronousRequestProcessing(): DeferredResult<ResponseEntity<*>> {
        LOGGER.info("GET /asynchronous-request")
        val deferred: DeferredResult<ResponseEntity<*>> = DeferredResult<ResponseEntity<*>>()

//        var forkJoinPool: ForkJoinPool = ForkJoinPool()
        ForkJoinPool.commonPool().submit {
            LOGGER.info("Processing request in new Thread")
            Thread.sleep(10000)
            deferred.setResult(ResponseEntity.ok("DISPLAY"))
        }
        LOGGER.info("before return deferred")
        return deferred
    }

    @GetMapping("/as")
    fun method(): DeferredResult<ResponseEntity<*>> {
        LOGGER.info("GET /asynchronous-request-2")
        val deferred: DeferredResult<ResponseEntity<*>> = DeferredResult<ResponseEntity<*>>(5000)

        deferred.onTimeout {
            LOGGER.info("me here")
            deferred.setResult(ResponseEntity.ok("TIME OUT SORRY"))
        }

        ForkJoinPool.commonPool().submit {
            LOGGER.info("Processing request in new Thread - 2")
            Thread.sleep(4000)
            deferred.setResult(ResponseEntity.ok("DISPLAY - 2"))
        }
        LOGGER.info("before return deferred - 2")
        return deferred
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<*> {
        LOGGER.info("GET /test")
        val deferred: DeferredResult<ResponseEntity<*>> = DeferredResult<ResponseEntity<*>>()

//        var forkJoinPool: ForkJoinPool = ForkJoinPool()
        ForkJoinPool.commonPool().submit {
            LOGGER.info("Processing TEST request in new Thread")
            Thread.sleep(10000)
//            deferred.setResult(ResponseEntity.ok("DISPLAY"))
            LOGGER.info("TEST Thread finished")
            ResponseEntity.ok("OK...")
        }
        LOGGER.info("before return response in TEST")
        return ResponseEntity.ok("TEST finished")
    }

    /* @GetMapping("/test")
     fun test(): ResponseEntity<*> {

 //        val noReturn: Runnable -> Unit = { println() }
         val r: (Runnable) -> Unit = { println() }
 //        val r: Runnable =
         val th: Thread = Thread()
     }*/


}