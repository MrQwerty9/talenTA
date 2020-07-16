package com.sstudio.talenta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sstudio.talenta.mvp.view.InputActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        var isCoach = false

        Handler().postDelayed({
            if (!isCoach) {
                val intent = Intent(this@SplashScreen, ListResultActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
