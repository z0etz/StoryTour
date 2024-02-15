package com.katja.storytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.widget.Toolbar
import com.katja.storytour.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), GeneralContract.View {

    lateinit var binding: ActivityMainBinding
    private lateinit var presenter: GeneralContract.Presenter
    private lateinit var model: GeneralModel
    private var seekbarMinutes = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the toolbar, set toolbar support
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        presenter = Presenter(this)
        presenter.loadImage()

        // Set a listener for the SeekBar, that updates the text below it
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekbarMinutes = binding.seekBar.progress * 15 + 30
                val hours = seekbarMinutes / 60
                val minutes = seekbarMinutes % 60
                var timeText = ""
                if(hours == 0) {
                    timeText = "$minutes minutes"
                }
                else if(minutes == 0){
                    if(hours == 1) {
                        timeText = "$hours hour"
                    }
                    else {
                        timeText = "$hours hours"
                    }
                }
                else {
                    if(hours == 1) {
                        timeText = "$hours hour, $minutes minutes"
                    }
                    else {
                        timeText = "$hours hours, $minutes minutes"
                    }
                }
                binding.adventureLengthString.text = timeText
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed
            }
        })
        binding.bStartAdventure.setOnClickListener {
            presenter.setupAdventure(seekbarMinutes)
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setBackground(imageResource: Int) {
        binding.layoutBackground.setBackgroundResource(imageResource)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings action
                return true
            }
            R.id.action_done -> {
                // Handle done action
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}