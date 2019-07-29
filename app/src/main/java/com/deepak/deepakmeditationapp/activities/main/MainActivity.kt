package com.deepak.deepakmeditationapp.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deepak.deepakmeditationapp.R
import com.deepak.deepakmeditationapp.activities.meditation.MeditationActivity
import com.deepak.deepakmeditationapp.utils.ApplicationConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListenersToCardViews()
    }

    private fun setOnClickListenersToCardViews(){
        main_destress_cv.setOnClickListener {
            startMeditationActivity(ApplicationConstants.DESTRESS)
        }

        main_calm_down_cv.setOnClickListener {
            startMeditationActivity(ApplicationConstants.CALM_DOWN)
        }
        main_focus_cv.setOnClickListener {
            startMeditationActivity(ApplicationConstants.FOCUS)
        }
        main_relax_cv.setOnClickListener {
            startMeditationActivity(ApplicationConstants.RELAX)
        }
    }

    private fun startMeditationActivity(key:String){
        val intent = Intent(this,MeditationActivity::class.java)
        intent.putExtra(ApplicationConstants.MEDITATION_BUNDLE_KEY,key)
        startActivity(intent)
    }
}
