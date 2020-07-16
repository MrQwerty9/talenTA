package com.sstudio.talenta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        toolbar_profile.title = "Profil Psikolog"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        btn_save_profile.setOnClickListener {
            Common.namePsycholog = et_name_psi.text.toString()
            Common.sipp = et_sipp_psi.text.toString()

            Toast.makeText(this, "Profil telah tersimpan", Toast.LENGTH_SHORT).show()
        }
    }
}