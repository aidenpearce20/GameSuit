package com.example.game_suit

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), Background, Alerts {

    private val imgRock by lazy { findViewById<ImageView>(R.id.imgRock) }
    private val imgPaper by lazy { findViewById<ImageView>(R.id.imgPaper) }
    private val imgScissor by lazy { findViewById<ImageView>(R.id.imgScissor) }

    private var choiceSelected: Boolean = false
    private val tvVersus by lazy { findViewById<TextView>(R.id.tvVersus) }

    private val imgComRock by lazy { findViewById<ImageView>(R.id.imgComRock) }
    private val imgComPaper by lazy { findViewById<ImageView>(R.id.imgComPaper) }
    private val imgComScissor by lazy { findViewById<ImageView>(R.id.imgComScissor) }

    private val imgRefresh by lazy { findViewById<ImageView>(R.id.imgRefresh) }
    val logic = LogicGame(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContentView(R.layout.activity_main)

        var dataView = arrayListOf<ImageView>(imgRock, imgPaper, imgScissor, imgRefresh)

        dataView.forEach { img ->
            img.setOnClickListener {
                if (img.tag == "imgRefresh" && choiceSelected) {
                    choiceSelected = false
                    logic.resetGame(this)
                } else if (!choiceSelected) {
                    logic.startGame(this, img, img.contentDescription.toString())
                    choiceSelected = true
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun resultBackground(player: ImageView?, com: String?, color: ColorStateList?) {
        if (!choiceSelected) {
            player?.backgroundTintList = color
            when (com) {
                "batu" -> imgComRock.backgroundTintList = color
                "gunting" -> imgComScissor.backgroundTintList = color
                "kertas" -> imgComPaper.backgroundTintList = color
            }
        }
    }

    override fun showAlertMessages(text: String) {
        println(text)
    }

    @SuppressLint("SetTextI18n")
    override fun dialogResult(value: String) {
        when (value) {
            "playerOne" -> {
                tvVersus.text = "Pemain 1\nMENANG!"
                tvVersus.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
                tvVersus.setBackgroundColor(ContextCompat.getColor(this, R.color.bgDialogGreen))
            }
            "COM" -> {
                tvVersus.text = "Pemain 2\nMENANG!"
                tvVersus.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
                tvVersus.setBackgroundColor(ContextCompat.getColor(this, R.color.bgDialogGreen))
            }
            "Draw" -> {
                tvVersus.text = "DRAW"
                tvVersus.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
                tvVersus.setBackgroundColor(ContextCompat.getColor(this, R.color.bgDialogBlue))
            }
            "VS" -> {
                tvVersus.text = "VS"
                tvVersus.setTextColor(ContextCompat.getColor(this, R.color.red))
                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80F)
                tvVersus.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            }
        }
    }
}