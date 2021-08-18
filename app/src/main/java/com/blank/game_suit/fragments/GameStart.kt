package com.blank.game_suit.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.blank.game_suit.classes.LogicGame
import com.blank.game_suit.classes.backToPreviousFragment
import com.example.game_suit.R
import com.blank.game_suit.classes.replaceFragmentFromActivity
import com.blank.game_suit.data.UserData
import com.blank.game_suit.interfaces.Alerts
import com.blank.game_suit.interfaces.Background
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class GameStart(private val fragContainer: Int) : Fragment(R.layout.fragment_game_start),
    Background, Alerts {

    private lateinit var bundle: Bundle
    private lateinit var imgRock: ImageView
    private lateinit var imgPaper: ImageView
    private lateinit var imgScissor: ImageView

    private var firstChoiceSelected: Boolean = false
    private var choiceSelected: Boolean = false

    private lateinit var closeGame: ImageView
    private lateinit var headerImage: ImageView
    private lateinit var tvVersus: TextView
    private lateinit var tvTitlePlayer: TextView
    private lateinit var tvOpponent: TextView

    private lateinit var imgComRock: ImageView
    private lateinit var imgComPaper: ImageView
    private lateinit var imgComScissor: ImageView

    private lateinit var imgRefresh: ImageView
    private val logic = LogicGame(this)

    private var firstPlayerChoice: String = ""
    private var secondPlayerChoice: String = ""
    private var selectedImageLeft: ImageView? = null
    private var selectedImageRight: ImageView? = null
    private lateinit var gamePlay: String
    private lateinit var username: String
    private lateinit var dataUser: UserData


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgRock = view.findViewById(R.id.imgRock)
        imgPaper = view.findViewById(R.id.imgPaper)
        imgScissor = view.findViewById(R.id.imgScissor)

        closeGame = view.findViewById(R.id.closeGame)
        headerImage = view.findViewById(R.id.headerImage)
        tvVersus = view.findViewById(R.id.tvVersus)
        tvTitlePlayer = view.findViewById(R.id.tvTitlePlayer)
        tvOpponent = view.findViewById(R.id.tvTitleOpponent)

        imgComRock = view.findViewById(R.id.imgComRock)
        imgComPaper = view.findViewById(R.id.imgComPaper)
        imgComScissor = view.findViewById(R.id.imgComScissor)

        imgRefresh = view.findViewById(R.id.imgRefresh)

        val dataView = arrayListOf(
            imgRock,
            imgPaper,
            imgScissor,
            imgRefresh,
            imgComRock,
            imgComPaper,
            imgComScissor
        )

        bundle = requireArguments()
        dataUser = bundle.getParcelable("key")!!
        gamePlay = dataUser.choice
        username = dataUser.name

        tvTitlePlayer.text = username
        tvOpponent.text = if (gamePlay == "player") "Pemain 2" else "CPU"


        // Dexter Permissions
        Dexter.withContext(context)
            .withPermission(Manifest.permission.INTERNET)
            .withListener(object : PermissionListener {

                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    Glide.with(context!!).load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
                        .into(headerImage)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(context, "ERROR BOSS GAK BISA LOAD GAMBAR", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            }).check()

        dataView.forEach { img ->
            val imgTag = img.tag

            img.setOnClickListener {

                when (imgTag) {
                    "imgRefresh" -> {
                        if (choiceSelected) {
                            onResetGame()
                        }
                    }
                    "imgRock", "imgScissor", "imgPaper" -> {
                        if (selectedImageLeft != null) {
                            selectedImageLeft!!.backgroundTintList =
                                changeColorStateList(R.color.bg_activity)
                        }

                        firstPlayerChoice = img.contentDescription.toString()
                        selectedImageLeft = img
                        selectedImageLeft!!.backgroundTintList =
                            changeColorStateList(R.color.bgSelected)
                        firstChoiceSelected = true

                        if (gamePlay == "cpu" && firstChoiceSelected) {
                            onStartGame()
                        }
                    }
                    "imgComRock", "imgComScissor", "imgComPaper" -> {
                        if (selectedImageRight != null) {
                            selectedImageRight!!.backgroundTintList =
                                changeColorStateList(R.color.bg_activity)
                        }

                        if (gamePlay == "player" && firstChoiceSelected) {
                            secondPlayerChoice = img.contentDescription.toString()
                            selectedImageRight = img
                            selectedImageRight!!.backgroundTintList =
                                changeColorStateList(R.color.bgSelected)
                            onStartGame()
                        }
                    }
                }
            }
        }

        closeGame.setOnClickListener {
            backToPreviousFragment(
                parentFragmentManager,
                ChooseOpponent(fragContainer, false),
                dataUser
            )
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onStartGame() {

        if (!choiceSelected && gamePlay == "player" && firstPlayerChoice.isNotEmpty() && secondPlayerChoice.isNotEmpty()) {
            logic.startGame(
                this,
                username,
                gamePlay,
                selectedImageLeft!!,
                firstPlayerChoice,
                selectedImageRight,
                secondPlayerChoice,
                context
            )
            choiceSelected = true
        } else if (!choiceSelected && gamePlay == "cpu" && firstPlayerChoice.isNotEmpty()) {
            logic.startGame(
                this,
                username,
                gamePlay,
                selectedImageLeft!!,
                firstPlayerChoice,
                null,
                "",
                context
            )
            choiceSelected = true
        }
    }

    private fun changeColorStateList(color: Int): ColorStateList? {
        return ContextCompat.getColorStateList(requireContext(), color)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun resultBackground(
        player: ImageView?,
        com: String?,
        scndPlayerImg: ImageView?,
        scndPlayerChc: String?,
        color: Int
    ) {
        player?.backgroundTintList = changeColorStateList(color)

        if (!choiceSelected) {
            when (com) {
                "Batu" -> imgComRock.backgroundTintList = changeColorStateList(color)
                "Gunting" -> imgComScissor.backgroundTintList = changeColorStateList(color)
                "Kertas" -> imgComPaper.backgroundTintList = changeColorStateList(color)
            }
        }
    }

    override fun showAlertMessages(text: String) {
        println(text)
    }

    private fun onResetGame() {
        choiceSelected = false
        logic.resetGame(this)
        firstPlayerChoice = ""
        selectedImageLeft = null
        secondPlayerChoice = ""
        selectedImageRight = null
        firstChoiceSelected = false
    }

    private fun toMenu(value: Boolean) {
        if (value) {
            replaceFragmentFromActivity(
                parentFragmentManager,
                ChooseOpponent(fragContainer, false),
                fragContainer,
                dataUser
            )
        }
    }

    private fun replayGame(value: Boolean) {
        if (value) {
            onResetGame()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun dialogResult(value: String, winner: String) {
        val dialogFragment =
            DialogResult.newInstance(winner, this@GameStart::toMenu, this@GameStart::replayGame)
        dialogFragment.isCancelable = false
        when (value) {
            "playerOne" -> {
                dialogFragment.show(childFragmentManager, winner)
//                tvVersus.text = "Pemain 1\nMENANG!"
//                tvVersus.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
//                tvVersus.setBackgroundColor(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.bgDialogGreen
//                    )
//                )
            }
            "COM" -> {
                dialogFragment.show(childFragmentManager, winner)
//                tvVersus.text = "Pemain 2\nMENANG!"
//                tvVersus.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
//                tvVersus.setBackgroundColor(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.bgDialogGreen
//                    )
//                )
            }
            "Draw" -> {
                dialogFragment.show(childFragmentManager, winner)
//                tvVersus.text = "DRAW"
//                tvVersus.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
//                tvVersus.setBackgroundColor(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.bgDialogBlue
//                    )
//                )
            }
//            "VS" -> {
//                tvVersus.text = "VS"
//                tvVersus.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
//                tvVersus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80F)
//                tvVersus.setBackgroundColor(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.bg_activity
//                    )
//                )
//            }
        }
    }
}