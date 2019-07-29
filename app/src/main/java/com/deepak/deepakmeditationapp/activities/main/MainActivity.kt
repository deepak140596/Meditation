package com.deepak.deepakmeditationapp.activities.main

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.deepak.deepakmeditationapp.R
import com.deepak.deepakmeditationapp.activities.login.LoginActivity
import com.deepak.deepakmeditationapp.activities.meditation.MeditationActivity
import com.deepak.deepakmeditationapp.utils.ApplicationConstants
import com.firebase.ui.auth.AuthUI
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_logout -> {

                logoutUser()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logoutUser(){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.title_are_you_sure))
            .setPositiveButton(getString(R.string.text_logout)) { _, _ ->
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        Toast.makeText(this,getString(R.string.msg_succesfull_logout), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }
            }.setNegativeButton(getString(R.string.text_cancel)){ _, _ ->}

        alertDialog.create().show()

    }
}
