package com.sstudio.talenta

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.talenta.Common.dataDummy
import com.sstudio.talenta.adapter.ResultAdapter
import com.sstudio.talenta.mvp.view.InputActivity
import com.sstudio.talenta.model.Result
import com.sstudio.talenta.mvp.view.ResultActivity.Companion.ID_EXTRA
import kotlinx.android.synthetic.main.activity_list_result.*
import kotlinx.android.synthetic.main.dialog_individu_profile.*
import java.text.SimpleDateFormat
import java.util.*

class ListResultActivity : AppCompatActivity() {

    lateinit var resultAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_result)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Daftar Peserta"
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            dialogUpdel()
        }

        resultAdapter = ResultAdapter(this)
        val sdfForFile = SimpleDateFormat("ddMMyyHHmmss")
        val currentDateForFile = sdfForFile.format(Date())

        resultAdapter.result = dataDummy
        rv_result.layoutManager = LinearLayoutManager(this)
        rv_result.adapter = resultAdapter
        resultAdapter.notifyDataSetChanged()
        swipe.setOnRefreshListener{
            resultAdapter.notifyDataSetChanged()
            swipe.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list_result, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_profil) {
            val mIntent = Intent(this, ProfileActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogUpdel(){
        val dialogAdd = Dialog(this)
        dialogAdd.setContentView(R.layout.dialog_individu_profile)

        dialogAdd.btn_individu_dialog_add_update.text = "TAMBAH"
        val sdfForFile = SimpleDateFormat("ddMMyyHHmmss")
        val currentDateForFile = sdfForFile.format(Date())
        dialogAdd.btn_individu_dialog_add_update.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra(ID_EXTRA, Result(
                dialogAdd.et_idpes.text.toString().toLong(),
                (dialogAdd.et_id_ind.text.toString()).toLong(),
                dialogAdd.et_name_psi.text.toString(),
                dialogAdd.et_institution_psi.text.toString(),
                dialogAdd.et_sipp_psi.text.toString(),
                dialogAdd.et_study.text.toString(),
                dialogAdd.et_purpose_test.text.toString(),
                null,
                null
            ))
            startActivity(intent)
            dialogAdd.dismiss()
        }
        dialogAdd.btn_individu_dialog_cancel.setOnClickListener {
            dialogAdd.dismiss()
        }
        dialogAdd.show()
    }

    override fun onResume() {
        super.onResume()
        resultAdapter.notifyDataSetChanged()
    }
}