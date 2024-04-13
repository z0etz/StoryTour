package com.katja.storytour

import android.content.Context

interface GeneralContract {
    interface View {
        fun setBackground(imageResource: Int)
    }

    interface Presenter {
        fun loadImage()

        fun setupAdventure(length: Int)

        fun setupLocation(storychoise: Storychoise? = null)

        fun endAdventure()

        fun documentStoryline(text: String)

        fun changeWaypoint()
        abstract fun calculateDistanceToWaypoint(context: Context): Int
    }
}