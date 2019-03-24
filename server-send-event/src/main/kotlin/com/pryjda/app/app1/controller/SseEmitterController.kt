package com.pryjda.app.app1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
class SseEmitterController {

    @GetMapping("/emitter")
    fun sendEmitter(): SseEmitter {
        val emitter: SseEmitter = SseEmitter()
        return emitter
    }


}