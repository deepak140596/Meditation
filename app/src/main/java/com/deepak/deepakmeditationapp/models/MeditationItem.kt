package com.deepak.deepakmeditationapp.models

class MeditationItem(var name :String = "",var doing_right_now : Int = 0 ){
    override fun toString(): String {
        return "MeditationItem(name='$name', doing_right_now=$doing_right_now)"
    }
}