package com.sstudio.talenta.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sstudio.talenta.R
import com.sstudio.talenta.model.Result
import com.sstudio.talenta.mvp.view.ResultActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.view.*
import kotlinx.android.synthetic.main.item_result.view.*

class ResultAdapter(private val context: Context) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    var result: List<Result> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_result, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = result[position]
        holder.txtName.text = "${result.name} / ${result.noTest}"
        holder.txtBirth.text = result.placeDateBirth.toString()
        holder.txtAddress.text = result.address

        holder.cvResult.setOnClickListener {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(ResultActivity.ID_EXTRA, result)
            context.startActivity(intent)
        }

        val highest = result.finalResult?.let { findHighest(it) }
        highest?.let { showResult(it, holder) }
    }

    private fun showResult(predic: DoubleArray, holder: ResultViewHolder){
        var textResult = ""
        for (i in predic.indices) {
            when {
                predic[i] == 0.0 -> textResult += "Teknik "
                predic[i] == 1.0 -> textResult += "Bahasa "
                predic[i] == 2.0 -> textResult += "Sosial "
                predic[i] == 3.0 -> textResult += "Seni "
                predic[i] == 4.0 -> textResult += "Medikal "
            }
        }
        holder.txtResult.text = textResult
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

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.tv_name_result
        val txtBirth = itemView.tv_birth_result
        val txtResult = itemView.tv_result
        val txtAddress = itemView.tv_address_result
        val cvResult = itemView.cv_item_comment
    }
}