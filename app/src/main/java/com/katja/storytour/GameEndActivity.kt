package com.katja.storytour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.katja.storytour.databinding.ActivityGameEndBinding

class GameEndActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityGameEndBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)
        presenter.loadImage()
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }
}