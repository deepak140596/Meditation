package com.deepak.deepakmeditationapp.activities.meditation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.deepak.deepakmeditationapp.R
import com.deepak.deepakmeditationapp.database.FirebaseRepository
import com.deepak.deepakmeditationapp.utils.ApplicationConstants

class MeditationActivity : AppCompatActivity() {
    val TAG = "MEDI_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        var action = intent.extras.getString(ApplicationConstants.MEDITATION_BUNDLE_KEY)
        if(action!=null){
            Log.e("MED_AC",action)
        }

        FirebaseRepository().getMeditationType(action).observe(this, Observer { meditationItem ->
            if(meditationItem != null){
                Toast.makeText(this,meditationItem.toString(),Toast.LENGTH_SHORT).show()
            }
        })

        FirebaseRepository().getSessions(action).observe(this, Observer { sessions ->
            if(sessions.isNotEmpty()){
                //Toast.makeText(this,sessions.size,Toast.LENGTH_SHORT).show()
                Log.d(TAG,"SESSION_SIZE: ${sessions.size}")
            }
        })
    }
}
