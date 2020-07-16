package com.sstudio.talenta

import com.sstudio.talenta.model.Result

object Common {
    const val varb_t = 18 //jumlah variabel
    const val training_t = 400 //jumlah data training
    const val layer_t = 1 //jumlah hidden layer
    const val a = 0.005 // learning rate
    const val min_error = 0.001 //minimal error
    const val neurHidd = 17
    const val multipleTrain = 30
    var w: Array<Array<Array<Array<Array<Double>>>>>? = null
    var b: Array<Array<Array<Array<Double>>>>? = null
    var namePsycholog = "Nama Psikolog"
    var sipp = "0"
    val dataDummy = mutableListOf<Result>()
}