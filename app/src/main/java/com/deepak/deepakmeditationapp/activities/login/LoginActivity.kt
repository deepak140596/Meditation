package com.deepak.deepakmeditationapp.activities.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.deepak.deepakmeditationapp.activities.main.MainActivity
import com.deepak.deepakmeditationapp.R
import com.deepak.deepakmeditationapp.utils.ApplicationConstants.Companion.RC_SIGN_IN
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    val TAG = "LOG_IN"
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.title_log_in)
    }

    override fun onResume() {
        super.onResume()
        mAuth = FirebaseAuth.getInstance()
        var user = mAuth!!.currentUser
        if(user==null) {
            chooseAuthProviders()
        }
        else {
            startMainActivity(user)
        }
    }

    private fun chooseAuthProviders(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                startMainActivity(user)
            } else {
                if(response!=null) {
                    Log.e(TAG, "Sign In Failed. " + response.error)
                }
                finish()
            }
        }
    }

    private fun startMainActivity(user: FirebaseUser? = null){
        if(user!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
