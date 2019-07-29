package com.deepak.deepakmeditationapp.activities.splashactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.deepak.deepakmeditationapp.R
import android.content.Intent
import com.deepak.deepakmeditationapp.MainActivity


class SplashActivity : AppCompatActivity() {

    val SPLASH_LENGTH = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_LENGTH)
    }
}
