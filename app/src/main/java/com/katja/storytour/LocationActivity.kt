package com.katja.storytour

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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

        binding.locationText.text = GeneralModel.location!!.description + " " +
        GeneralModel.location!!.choices[0].description + " or " +
                GeneralModel.location!!.choices[1].description.replaceFirstChar { it.lowercase() } + "?"

        binding.bLocation1.text = GeneralModel.location!!.choices[0].name
        binding.bLocation2.text = GeneralModel.location!!.choices[1].name

        binding.bLocation1.setOnClickListener {
            presenter.documentStoryline(GeneralModel.location!!.description)
            presenter.documentStoryline(GeneralModel.location!!.choices[0].name)
            presenter.setupLocation(GeneralModel.location!!.choices[0])
            val intent = Intent(this, WaypointActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.bLocation2.setOnClickListener {
            presenter.documentStoryline(GeneralModel.location!!.description)
            presenter.documentStoryline(GeneralModel.location!!.choices[1].name)
            presenter.setupLocation(GeneralModel.location!!.choices[1])
            val intent = Intent(this, WaypointActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
            DialogUtils.showQuitConfirmationDialog(this) {
            presenter.endAdventure()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}