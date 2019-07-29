package com.deepak.deepakmeditationapp.models

class SessionItem (var imageLink :String = "", var link :String = ""){
    override fun toString(): String {
        return "SessionItem(imageLink='$imageLink', link='$link')"
    }
}