package com.pryjda.app.app2.service

import com.pryjda.app.app2.model.DataSet
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class DataSetService {

    val dataList: MutableList<DataSet> = mutableListOf()

    @PostConstruct
    fun setup() = createDataSet()


    fun createDataSet(): Iterable<DataSet> {
        val name: String = "my text"
        for (i in 1..10) {
            this.dataList.add(DataSet(i, name + " $i"))
        }
        return dataList
    }
}