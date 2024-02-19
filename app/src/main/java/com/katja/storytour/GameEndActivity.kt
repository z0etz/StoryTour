package com.katja.storytour

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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

        binding.bFinish.setOnClickListener {
            DialogUtils.showQuitConfirmationDialog(this) {
                presenter.endAdventure()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
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