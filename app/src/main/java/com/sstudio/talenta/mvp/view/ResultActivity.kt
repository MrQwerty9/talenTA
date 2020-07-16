package com.sstudio.talenta.mvp.view

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hadiidbouk.charts.BarData
import com.sstudio.talenta.Common
import com.sstudio.talenta.R
import com.sstudio.talenta.model.Result
import com.sstudio.talenta.mvp.ToPDF
import com.sstudio.talenta.mvp.presenter.TestingPresenterImpl
import com.sstudio.talenta.mvp.presenter.TestingViewModel
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.dialog_individu_profile.*
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class ResultActivity : AppCompatActivity(), MyAsyncCallback {

    companion object {
        val INPUT_EXTRA = "input_extra"
        val ID_EXTRA = "id_extra"
        lateinit var context: Context
    }

    private lateinit var viewModel: TestingViewModel
    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        context = this

        result = intent.getParcelableExtra<Result>(ID_EXTRA)
        pb_progress.visibility = View.VISIBLE
        viewModel = ViewModelProviders.of(this)
            .get(TestingViewModel::class.java)
        val inputArray = result?.potencyValue
//        val inputArray= doubleArrayOf(0.4,0.4,0.4,0.8,0.4,0.4,0.6,0.4,0.4,1.0,0.8,1.0,0.8,0.8,1.0,0.8,0.4,0.8)

        result?.finalResult?.let {
            if (it.isNotEmpty()) {
                Log.d("myTag", "final result not empty")
            }else
                Log.d("myTag", "final result empty")
        }
        Log.d("myTag", "final result ${result?.finalResult}")

        if (result?.finalResult == null) {
            if (viewModel.dataList == null && !viewModel.isProcess) {
                inputArray?.let {
                    val demoAsync = DemoAsync(this)
                    demoAsync.execute(it)
                    viewModel.isProcess = true
                }
            } else if (viewModel.dataList != null) {
                buildChart(viewModel.dataList!!)
            }
        }else
            onPostExecute(result?.finalResult)

        btn_savepdf.setOnClickListener {
            result?.let { rst ->
                val toPDF = ToPDF(this, rst)
                toPDF.toPdf()
            }
        }

        suggestion.setOnClickListener {
            dialogSuggestion()
        }
    }

    override fun onPostExecute(finalResult: Array<Double>?) {
        viewModel.dataList = finalResult

        finalResult?.let { predic ->
            buildChart(predic)
            val highest = findHighest(predic)
            showConclusion(highest)
            result?.finalResult = predic
        }
        if (viewModel.isProcess){
            Common.dataDummy.add(Result(result!!.id, result?.noTest, result!!.name, result?.placeDateBirth, result?.address, result?.study, result?.purposeTest, result?.potencyValue, result?.finalResult))
        }
        viewModel.isProcess = false
    }

    private fun showConclusion(predic: DoubleArray) {
        for (i in predic.indices) {
            when {
                predic[i] == 0.0 -> conclusion_teknik.visibility = View.VISIBLE
                predic[i] == 1.0 -> conclusion_bahasa.visibility = View.VISIBLE
                predic[i] == 2.0 -> conclusion_sosial.visibility = View.VISIBLE
                predic[i] == 3.0 -> conclusion_seni.visibility = View.VISIBLE
                predic[i] == 4.0 -> conclusion_medikal.visibility = View.VISIBLE
            }
        }
        tv_id_name.text = result?.name
        tv_id_address.text = result?.address
        tv_id_birth.text = result?.placeDateBirth
        tv_id_notest.text = result?.noTest.toString()
        tv_id.text = result?.id.toString()
        const_id_individu.visibility = View.VISIBLE
        btn_savepdf.visibility = View.VISIBLE
        suggestion.visibility = View.VISIBLE
    }

    private fun dialogSuggestion() {
        val dialogAdd = Dialog(this)
        dialogAdd.setContentView(R.layout.dialog_suggestion)

        dialogAdd.btn_individu_dialog_add_update.text = "KIRIM"
        dialogAdd.btn_individu_dialog_add_update.setOnClickListener {
            Toast.makeText(context, "Saran telah terkirim", Toast.LENGTH_SHORT).show()
            dialogAdd.dismiss()
        }
        dialogAdd.btn_individu_dialog_cancel.setOnClickListener {
            dialogAdd.dismiss()
        }
        dialogAdd.show()
    }

    private fun findHighest(predic: Array<Double>): DoubleArray {
        var ss = doubleArrayOf()
        var j = predic[0]
        for (i in 1 until predic.size) {
            if (j < predic[i]) {
                j = predic[i]
            }
        }
        for (i in predic.indices) {
            if (j == predic[i]) {
                ss += i.toDouble()
            }
        }
        return ss
    }

    private fun buildChart(predic: Array<Double>) {
        val dataList: ArrayList<BarData> = ArrayList()
        var data = BarData("Teknik", (predic[0] / 10).toFloat(), "${predic[0].toInt()}%")
        dataList.add(data)

        data = BarData("Bahasa", (predic[1] / 10).toFloat(), "${predic[1].toInt()}%")
        dataList.add(data)

        data = BarData("Sosial", (predic[2] / 10).toFloat(), "${predic[2].toInt()}%")
        dataList.add(data)

        data = BarData("Seni", (predic[3] / 10).toFloat(), "${predic[3].toInt()}%")
        dataList.add(data)

        data = BarData("Medikal", (predic[4] / 10).toFloat(), "${predic[4].toInt()}%")
        dataList.add(data)

        pb_progress.visibility = View.GONE
        chartProgressBar.setDataList(dataList)
        chartProgressBar.build()
    }
    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        finish()
    }

    private class DemoAsync(myAsyncCallback: MyAsyncCallback) :
        AsyncTask<DoubleArray, Void, Array<Double>>() {
        private val myListener = WeakReference(myAsyncCallback)

        override fun doInBackground(vararg p0: DoubleArray?): Array<Double> {
            val testing = TestingPresenterImpl(context, p0[0])
//            Log.d("mytag","88 ${inputArray[17]}")
            for (i in 0 until 10) {
                thread {
                    repeat(3) {
                        var idx = i
                        if (it > 1) idx += 20
                        else if (it > 0) idx += 10
                        testing.start(idx)
                    }
                    Log.d("mytag", "for $i")
                }
            }
            while (testing.result() == null) {
                Thread.sleep(1000)
            }
            return testing.result() as Array<Double>
        }

        override fun onPostExecute(finalResult: Array<Double>?) {
            super.onPostExecute(finalResult)
            val myListener = this.myListener.get()
            finalResult?.let {
                myListener?.onPostExecute(it)
                Toast.makeText(context, "Data tersimpan", Toast.LENGTH_LONG).show()
            }
        }
    }
}

internal interface MyAsyncCallback {
    fun onPostExecute(finalResult: Array<Double>?)
}
