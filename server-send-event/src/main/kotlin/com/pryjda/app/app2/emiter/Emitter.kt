package com.pryjda.app.app2.emiter

import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Component("emitter")
class Emitter: SseEmitter() {

}