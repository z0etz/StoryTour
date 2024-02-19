package com.katja.storytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.katja.storytour.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityLocationBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the toolbar, set toolbar support
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        presenter = Presenter(this)
        presenter.loadImage()

        binding.locationText.text = GeneralModel.location!!.description

        binding.bLocation1.setOnClickListener {
            presenter.setupLocation(GeneralModel.location!!.choices[0])
            val intent = Intent(this, WaypointActivity::class.java)
            startActivity(intent)
        }

        binding.bLocation2.setOnClickListener {
            presenter.setupLocation(GeneralModel.location!!.choices[1])
            val intent = Intent(this, WaypointActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }
}