package com.sstudio.talenta.mvp.presenter

import android.content.Context
import android.util.Log
import com.sstudio.talenta.Common
import com.sstudio.talenta.Common.layer_t
import com.sstudio.talenta.Common.min_error
import com.sstudio.talenta.Common.multipleTrain
import com.sstudio.talenta.Common.neurHidd
import com.sstudio.talenta.Common.training_t
import com.sstudio.talenta.Common.varb_t
import com.sstudio.talenta.Matrix
import com.sstudio.talenta.R
import java.io.InputStream
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.round

class TestingPresenterImpl(private val context: Context, private val dataTest: DoubleArray?) :
    TestingPresenter {
    private var predic = Matrix.matrix1D(multipleTrain)
    val cate = arrayOf(0.1, 0.3, 0.5, 0.7, 0.9)
    var incOutput = -1
    var finish = false

    override fun start(idx: Int) {
        val sourceWeight = sourceWeight()
        val sourceBias = sourceBias()
        val w =
            Matrix.matrix4D(
                training_t,
                layer_t + 1,
                varb_t,
                varb_t
            )
        val inputStream: InputStream = context.resources.openRawResource(sourceWeight[idx])
        val inputBias: InputStream = context.resources.openRawResource(sourceBias[idx])
        var line = 0
        inputStream.bufferedReader()
            .forEachLine {
                var i = 0
                var j = 0
                var k = 0
                val data = it.split(",") //koma sebagai pemisah
                for (dt in data.indices) { //tiap data
                    var neuron = neurHidd - 1
                    var neuronLeft = varb_t - 1
                    if (i == layer_t) {
                        neuron = -1
                        neuronLeft = neurHidd - 1
                    }
                    if (k > neuron) {
                        j++
                        k = 0
                    }
                    if (j > neuronLeft) {
                        i++
                        j = 0
                    }
                    w[line][i][j][k] = data[dt].toDouble()
                    k++
                }
                line++
            }
        val b = Matrix.matrix3D(
            training_t,
            layer_t + 1,
            varb_t
        ); //bias
        line = 0
        inputBias.bufferedReader()
            .forEachLine {
                var i = 0
                var j = 0
                val data = it.split(",") //koma sebagai pemisah
                for (dt in data.indices) { //tiap data
                    var neuron = neurHidd - 1
                    if (i == layer_t) {
                        neuron = -1
                    }
                    if (j > neuron) {
                        i++
                        j = 0
                    }
                    b[line][i][j] = data[dt].toDouble()
                    j++
                }
                line++
            }
        test(w, b)
    }

    private fun test(w: Array<Array<Array<Array<Double>>>>, b: Array<Array<Array<Double>>>) {
        Log.d("mytag", "teststart $incOutput")
        var z = Matrix.matrix2D(layer_t + 1, neurHidd); //hidden layer
        var yTest = DoubleArray(training_t) //output test
        //feedforward
        for (k in 0 until training_t) { //jumlah dt train
            z = Matrix.matrix2D(layer_t + 1, neurHidd); //hidden layer
            yTest[k] = 0.0 //output
            for (l in 0 until layer_t) { //as layer
                //Setiap neuron pada lapis tersembunyi (Zj) menjumlahkan bobot-bobot sinyal masukan
                for (j in 0 until neurHidd) { //as hidden
                    for (i in 0 until varb_t) { //as input
                        if (l == 0) { //first
                            z[l][j] += dataTest!![i] * w[k][l][i][j]
                        } else {
                            z[l][j] += z[l - 1][i] * w[k][l][i][j]
                        }
                    }
                    z[l][j] += b[k][l][j]

                    //aktivasi sigmoid biner
                    z[l][j] = 1 / (1 + exp(-z[l][j]))
                }
                //
            }

            //Setiap neuron keluaran (Yk) menjumlahkan bobot sinyal yang masuk
            for (i in 0 until neurHidd) { //as hidden
                yTest[k] += z[layer_t - 1][i] * w[k][layer_t][i][0]
            }
            yTest[k] += b[k][layer_t][0]

            //aktivasi sigmoid biner
            yTest[k] = 1 / (1 + exp(-yTest[k]))
            //
        }

        incOutput++
        Log.d("mytag", "test $incOutput")
        predic[incOutput] = findClosest(yTest)
        if (incOutput >= multipleTrain - 1) {
            result()
//            testingView.result(percentage(predic))
            finish = true
        }
        println("prediksi $incOutput: ${getCateStr(predic[incOutput])} | \t")
    }

    fun result(): Array<Double>? {
        val percentage = percentage(predic)
        print(", prediksi: teknik = ${percentage[0]}, bahasa = ${percentage[1]}, sosial = ${percentage[2]}, seni = ${percentage[3]}, medikal = ${percentage[4]}")
        return if (finish)
            percentage
        else
            null
    }

    private fun findClosest(yTest: DoubleArray): Double {
        var predic = 0.0
        var smallest = 100.0
        var cateActual = Matrix.matrix1D(training_t)

        for (i in cate.indices) {
            for (j in yTest.indices) {

                val errr = abs(abs(yTest[j] - cate[i]) - min_error)
                if (errr <= smallest) {
                    smallest = errr
                    predic = cate[i]
                }
            }
        }
        return predic
    }

    fun percentage(arr: Array<Double>): Array<Double> {
        var teknik = 0.0
        var bahasa = 0.0
        var sosial = 0.0
        var seni = 0.0
        var medikal = 0.0
        for (i in arr.indices) {
            if (arr[i] == 0.1)
                teknik++
            else if (arr[i] == 0.3)
                bahasa++
            else if (arr[i] == 0.5)
                sosial++
            else if (arr[i] == 0.7)
                seni++
            else if (arr[i] == 0.9)
                medikal++
        }
        teknik = round(teknik / multipleTrain * 100)
        bahasa = round(bahasa / multipleTrain * 100)
        sosial = round(sosial / multipleTrain * 100)
        seni = round(seni / multipleTrain * 100)
        medikal = round(medikal / multipleTrain * 100)
        return arrayOf(teknik, bahasa, sosial, seni, medikal)
    }

    fun getCateStr(y: Double): String {
        when (y) {
            0.1 -> {
                return "teknik"
            }
            0.3 -> {
                return "bahasa"
            }
            0.5 -> {
                return "sosial"
            }
            0.7 -> {
                return "seni"
            }
            0.9 -> {
                return "medikal"
            }
        }
        return ""
    }

    private fun sourceWeight(): Array<Int> {
        return arrayOf(
            R.raw.weight0,
            R.raw.weight1,
            R.raw.weight2,
            R.raw.weight3,
            R.raw.weight4,
            R.raw.weight5,
            R.raw.weight6,
            R.raw.weight7,
            R.raw.weight8,
            R.raw.weight9,
            R.raw.weight10,
            R.raw.weight11,
            R.raw.weight12,
            R.raw.weight13,
            R.raw.weight14,
            R.raw.weight15,
            R.raw.weight16,
            R.raw.weight17,
            R.raw.weight18,
            R.raw.weight19,
            R.raw.weight20,
            R.raw.weight21,
            R.raw.weight22,
            R.raw.weight23,
            R.raw.weight24,
            R.raw.weight25,
            R.raw.weight26,
            R.raw.weight27,
            R.raw.weight28,
            R.raw.weight29
        )
    }

    private fun sourceBias(): Array<Int> {
        return arrayOf(
            R.raw.bias0,
            R.raw.bias1,
            R.raw.bias2,
            R.raw.bias3,
            R.raw.bias4,
            R.raw.bias5,
            R.raw.bias6,
            R.raw.bias7,
            R.raw.bias8,
            R.raw.bias9,
            R.raw.bias10,
            R.raw.bias11,
            R.raw.bias12,
            R.raw.bias13,
            R.raw.bias14,
            R.raw.bias15,
            R.raw.bias16,
            R.raw.bias17,
            R.raw.bias18,
            R.raw.bias19,
            R.raw.bias20,
            R.raw.bias21,
            R.raw.bias22,
            R.raw.bias23,
            R.raw.bias24,
            R.raw.bias25,
            R.raw.bias26,
            R.raw.bias27,
            R.raw.bias28,
            R.raw.bias29
        )
    }
}