package com.pryjda.app.app2.controller

import com.pryjda.app.app2.model.DataSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.ConcurrentMap
import java.util.logging.Logger

@RestController
class SseEmitterControllerNo2(@Autowired val dataSet: DataSet) : SseEmitter() {

    val emitterList: ConcurrentLinkedDeque<SseEmitter> = ConcurrentLinkedDeque()
    val emitterMap: ConcurrentMap<String, SseEmitter> = ConcurrentHashMap()
    val LOGGER: Logger = Logger.getLogger(SseEmitterControllerNo2::class.toString())

    @GetMapping("/get-emitter")
    fun sendEmitter(@RequestParam index: String): SseEmitter {
        LOGGER.info("clicked: GET /get-emitter")
        val emitter: SseEmitter = SseEmitter(60000)
//        emitterList.add(emitter)
        emitterMap.put(index, emitter)

        LOGGER.info("returned: emitter")
        return emitter
    }

    @GetMapping("/send-by-emitter")
    fun sendByEmitter(@RequestParam index: String) {
        LOGGER.info("clicked: GET /send-by-emitter")
//        emitterList.forEach { it.send(dataSet) }
        val newDataSet:DataSet = DataSet(index.toInt(), "data set")
        emitterMap[index]
                ?.send(SseEmitter.event()
                        .data(newDataSet)
                        .name("dataset-for-$index-created")
                        .id(newDataSet.hashCode().toString()))
    }

    override fun complete() {
        LOGGER.info("inside complete fun")
//        super.complete()
    }

    override fun onTimeout(callback: Runnable) {
        LOGGER.info("inside onTimeout fun")
//        super.onTimeout(callback)
    }


    @GetMapping("/send")
    fun doIt() {
        LOGGER.info("clicked: GET /send")
//        emitterList.forEach { it.send(dataSet) }
        emitterList.forEach {
            it.send(SseEmitter.event()
                    .data(dataSet)
                    .name("dataset-created")
                    .id(dataSet.hashCode().toString()))
        }

    }
}

//private fun <K, V> ConcurrentMap<K, V>.getOrPut(key: K, defaultValue: V) {
//
//}
