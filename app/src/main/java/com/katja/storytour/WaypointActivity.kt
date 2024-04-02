package com.katja.storytour

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.katja.storytour.databinding.ActivityWaypointBinding

class WaypointActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityWaypointBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaypointBinding.inflate(layoutInflater)
        setContentView(binding.root)

    // Find the toolbar, set toolbar support
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    presenter = Presenter(this)
    presenter.loadImage()

    binding.waypointText.text = GeneralModel.location!!.waypoints[0].description

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