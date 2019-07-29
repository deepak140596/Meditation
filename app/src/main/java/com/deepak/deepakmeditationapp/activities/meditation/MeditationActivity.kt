package com.deepak.deepakmeditationapp.activities.meditation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.deepak.deepakmeditationapp.R
import com.deepak.deepakmeditationapp.database.FirebaseRepository
import com.deepak.deepakmeditationapp.database.SharedPreferenceDB
import com.deepak.deepakmeditationapp.models.SessionItem
import com.deepak.deepakmeditationapp.utils.ApplicationConstants
import com.example.jean.jcplayer.model.JcAudio
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meditation.*

class MeditationActivity : AppCompatActivity() {

    val TAG = "MEDI_ACTIVITY"
    lateinit var firebaseRepository : FirebaseRepository
    var meditationType :String ?= ""
    var restartMusic = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        var bundle = intent.extras
        if(bundle != null){
            meditationType = bundle.getString(ApplicationConstants.MEDITATION_BUNDLE_KEY)
        } else {
            meditationType = SharedPreferenceDB.getMeditationType(this)
            restartMusic = false
        }

        firebaseRepository = FirebaseRepository()

        if(meditationType != null){
            Log.e("MED_AC",meditationType)
            observeMeditationType(meditationType)
            observeSessions(meditationType)
        }

    }

    fun observeMeditationType(meditationType : String?){
        firebaseRepository.getMeditationType(meditationType!!).observe(this, Observer { meditationItem ->
            if(meditationItem != null){
                Toast.makeText(this,meditationItem.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeSessions(meditationType: String?){
        firebaseRepository.getSessions(meditationType!!).observe(this, Observer { sessions ->
            if(sessions.size != 0){
                //Toast.makeText(this,sessions.size,Toast.LENGTH_SHORT).show()
                Log.d(TAG,"SESSION_SIZE: ${sessions.size}")

                if(restartMusic) {
                    playMusic(sessions[0])
                }
                setupBackgroundImage(sessions[0])

                if(meditation_progress_bar.isVisible){
                    meditation_progress_bar.visibility = View.GONE
                }
            }
        })
    }

    private fun playMusic(sessionItem: SessionItem){
        val audio = ArrayList<JcAudio>()
        audio.add(JcAudio.createFromURL("Music",sessionItem.link))

        meditation_music_player_view.kill()
        meditation_music_player_view.initPlaylist(audio,null)
        meditation_music_player_view.createNotification()
    }

    private fun setupBackgroundImage(sessionItem: SessionItem){
        Picasso.get().load(sessionItem.imageLink).into(meditation_image_view)
    }
}