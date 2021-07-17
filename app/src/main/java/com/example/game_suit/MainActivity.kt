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

    private lateinit var imgRock: ImageView
    private lateinit var imgPaper: ImageView
    private lateinit var imgScissor: ImageView

    private var choiceSelected: Boolean = false
    private lateinit var tvVersus: TextView

    private lateinit var imgComRock: ImageView
    private lateinit var imgComPaper: ImageView
    private lateinit var imgComScissor: ImageView

    private lateinit var imgRefresh: ImageView

    val logic = LogicGame(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContentView(R.layout.activity_main)

        imgRock = findViewById(R.id.imgRock)
        imgPaper = findViewById(R.id.imgPaper)
        imgScissor = findViewById(R.id.imgScissor)

        imgRefresh = findViewById(R.id.imgRefresh)
        tvVersus = findViewById(R.id.tvVersus)

        imgComRock = findViewById(R.id.imgComRock)
        imgComPaper = findViewById(R.id.imgComPaper)
        imgComScissor = findViewById(R.id.imgComScissor)


        imgRock.setOnClickListener {
            if (!choiceSelected) {
                logic.startGame(this, imgRock, "batu")
                choiceSelected = true
            }
        }

        imgPaper.setOnClickListener {
            if (!choiceSelected) {
                logic.startGame(this, imgPaper, "kertas")
                choiceSelected = true
            }
        }

        imgScissor.setOnClickListener {
            if (!choiceSelected) {
                logic.startGame(this, imgScissor, "gunting")
                choiceSelected = true
            }
        }

        imgRefresh.setOnClickListener {
            choiceSelected = false
            logic.resetGame(this)
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