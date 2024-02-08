package com.katja.storytour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.katja.storytour.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityLocationBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)
        presenter.loadImage()
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }
}