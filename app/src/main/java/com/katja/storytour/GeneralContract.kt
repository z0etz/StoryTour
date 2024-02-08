package com.katja.storytour

interface GeneralContract {
    interface View {
        fun setBackground(imageResource: Int)
    }

    interface Presenter {
        fun loadImage()
    }
}