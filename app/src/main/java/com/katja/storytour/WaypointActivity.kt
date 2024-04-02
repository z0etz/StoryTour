package com.katja.storytour

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.katja.storytour.databinding.ActivityWaypointBinding
import kotlinx.coroutines.*

class WaypointActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityWaypointBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel

    private var currentDistance: Int = -1

    private val updateInterval = 1000L // Update interval in milliseconds

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
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

        // Start updating distance periodically
        UpdateDistance(this)

        //Calculate and display distance
        val currentLocation = PositionManager.getCurrentLocation(this)
        if (currentLocation != null) {
            val distance = PositionManager.calculateDistance(currentLocation)
            binding.metersLeft.text = "You are $distance meters away"
        } else {
            binding.metersLeft.text = "Unable to get GPS location"
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

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the coroutine scope to avoid memory leaks
        coroutineScope.cancel()
    }

    private fun UpdateDistance(context: Context) {
        coroutineScope.launch {
            while (true) {
                val distance = presenter.calculateDistanceToWaypoint(context)
                if (distance != currentDistance) {
                    currentDistance = distance
                    withContext(Dispatchers.Main) {
                        updateDistanceText(distance)
                    }
                    if (distance in 0..19) {
                        nextWaypoint()
                    }
                }
                println("Distance updated $distance")
                delay(updateInterval)
            }
        }
    }

    private fun updateDistanceText(distance: Int) {
        val text = if (distance != -1) {
            "You are $distance meters away"
        } else {
            "Unable to get GPS location"
        }
        binding.metersLeft.text = text
    }

    fun nextWaypoint() {
        presenter.changeWaypoint()

        if (!GeneralModel.location!!.waypoints.isEmpty()) {
            val intent = Intent(this, WaypointActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}