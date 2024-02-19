package com.katja.storytour

interface GeneralContract {
    interface View {
        fun setBackground(imageResource: Int)
    }

    interface Presenter {
        fun loadImage()

        fun setupAdventure(length: Int)

        fun setupLocation(storychoise: Storychoise? = null)
    }
}