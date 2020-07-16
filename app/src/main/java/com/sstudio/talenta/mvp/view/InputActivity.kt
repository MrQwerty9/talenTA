package com.sstudio.talenta.mvp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.talenta.ListResultActivity
import com.sstudio.talenta.R
import kotlinx.android.synthetic.main.activity_input.*
import com.sstudio.talenta.model.Result

class InputActivity : AppCompatActivity() {

    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        init()
        result = intent.getParcelableExtra<Result>(ResultActivity.ID_EXTRA)
        if (result == null)
            startActivity(Intent(this, ListResultActivity::class.java))
        toolbar.title = result?.name

        btn_process.setOnClickListener {
            val dataInput = addInput()
            if (dataInput != null) {
                val intent = Intent(this, ResultActivity::class.java)
//                intent.putExtra(ResultActivity.INPUT_EXTRA, dataInput)
                result?.potencyValue = dataInput
                intent.putExtra(ResultActivity.ID_EXTRA, result)
//                Log.d("mytag", "arrayinput ${spin_test.selectedIndex}")
                for (i in dataInput.indices) {
                    Log.d("mytag", "arrayinput ${dataInput[i]}")
                }
            startActivity(intent)
            }else{
                Toast.makeText(this, "Harap isi semua bidang", Toast.LENGTH_SHORT).show();
            }
            finish()
        }
    }

    private fun addInput(): DoubleArray?{
        var arrayInput = DoubleArray(18)
        arrayInput[0] = spin_keputusan.selectedIndex.toDouble()
        arrayInput[1] = spin_dayapaham.selectedIndex.toDouble()
        arrayInput[2] = spin_logika.selectedIndex.toDouble()
        arrayInput[3] = spin_mengingat.selectedIndex.toDouble()
        arrayInput[4] = spin_berhitung.selectedIndex.toDouble()
        arrayInput[5] = spin_berhitungritmis.selectedIndex.toDouble()
        arrayInput[6] = spin_analisasintesa.selectedIndex.toDouble()
        arrayInput[7] = spin_daya_bayang_ruang.selectedIndex.toDouble()
        arrayInput[8] = spin_kecepatan_kerja.selectedIndex.toDouble()
        arrayInput[9] = spin_ketahanan_kerja.selectedIndex.toDouble()
        arrayInput[10] = spin_ketelitian_kerja.selectedIndex.toDouble()
        arrayInput[11] = spin_sistematika_kerja.selectedIndex.toDouble()
        arrayInput[12] = spin_ketekunan.selectedIndex.toDouble()
        arrayInput[13] = spin_stabilitas_emosi.selectedIndex.toDouble()
        arrayInput[14] = spin_tanggung_jawab.selectedIndex.toDouble()
        arrayInput[15] = spin_kemandirian.selectedIndex.toDouble()
        arrayInput[16] = spin_bersosialisasi.selectedIndex.toDouble()
        arrayInput[17] = spin_bekerja_sama.selectedIndex.toDouble()
        var value: Double
        for (i in arrayInput.indices) {
            value = when(arrayInput[i]) {
                1.0 -> 1.0
                2.0 -> 0.8
                3.0 -> 0.6
                4.0 -> 0.4
                5.0 -> 0.2
                6.0 -> 0.0
                else -> {
                    Log.d("mytag", "eroraddinput")
                    return null
                }
            }
            arrayInput[i] = value
        }
        return arrayInput
    }

    private fun init() {
        val value = arrayOf("","Tinggi Sekali","Tinggi", "Sedang Tinggi","Sedang","Sedang Rendah","Rendah")

        spin_keputusan.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_dayapaham.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_logika.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_mengingat.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_berhitung.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_berhitungritmis.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_analisasintesa.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_daya_bayang_ruang.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_kecepatan_kerja.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_ketahanan_kerja.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_ketelitian_kerja.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_sistematika_kerja.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_ketekunan.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_stabilitas_emosi.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_tanggung_jawab.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_kemandirian.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_bersosialisasi.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
        spin_bekerja_sama.setItems(value[0],value[1],value[2],value[3],value[4],value[5],value[6])
  }

}
