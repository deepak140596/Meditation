package com.deepak.deepakmeditationapp.database

import android.content.Context
import android.preference.PreferenceManager
import com.deepak.deepakmeditationapp.utils.ApplicationConstants

class SharedPreferenceDB {
    companion object{
        fun saveMeditationType(context: Context, meditationType : String){
            var editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(ApplicationConstants.MEDITATION_BUNDLE_KEY,meditationType)

            editor.apply()
            editor.commit()
        }

        fun getMeditationType(context: Context): String?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(ApplicationConstants.MEDITATION_BUNDLE_KEY,"")
        }
    }
}