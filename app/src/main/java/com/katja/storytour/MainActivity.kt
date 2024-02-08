package com.katja.storytour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.katja.storytour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityMainBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)
        presenter.loadImage()
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }
}