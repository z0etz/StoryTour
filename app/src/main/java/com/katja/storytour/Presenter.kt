package com.katja.storytour

class Presenter(private val view: GeneralContract.View) : GeneralContract.Presenter {



    override fun loadImage() {
        val backgroundImage = GeneralModel.backgroundImage
        view.setBackground(backgroundImage)
    }
}
