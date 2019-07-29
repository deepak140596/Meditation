package com.deepak.deepakmeditationapp.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deepak.deepakmeditationapp.models.MeditationItem
import com.deepak.deepakmeditationapp.models.SessionItem
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    val TAG = "FIREB_REPO"
    var firestoreDb = FirebaseFirestore.getInstance()

    fun getMeditationType(meditationType : String): LiveData<MeditationItem> {
        var meditationLiveData = MutableLiveData<MeditationItem>()
        var documentReference = firestoreDb.collection("meditation").document(meditationType)
        documentReference.get().addOnSuccessListener { documentSnapshot ->

            if(documentSnapshot != null){
                var meditationItem = documentSnapshot.toObject(MeditationItem::class.java)
                Log.d(TAG,"Doing right now : ${meditationItem!!}")
                meditationLiveData.value = meditationItem
            }
        }.addOnFailureListener {exception ->
            Log.d(TAG, "get failed with ", exception)
            meditationLiveData.value = null
        }

        return meditationLiveData
    }

    fun getSessions(meditationType : String): LiveData<List<SessionItem>> {
        var sessionsLiveData = MutableLiveData<List<SessionItem>>()
        var collectionReference = firestoreDb.collection("meditation")
            .document(meditationType).collection("session")

        collectionReference.get().addOnSuccessListener { querySnapshot ->
            var mutableList = mutableListOf<SessionItem>()
            for (document in querySnapshot){
                mutableList.add(document.toObject(SessionItem::class.java))
            }

            sessionsLiveData.value = mutableList
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
            sessionsLiveData.value = null
        }

        return sessionsLiveData
    }


}