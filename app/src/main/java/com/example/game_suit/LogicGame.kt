package com.example.game_suit

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat

open class LogicGame(private val background: Background) : CountDown() {
    private var player1: String? = null
    private var player2: String? = null
    private var player1Element: ImageView? = null
    private lateinit var message: String
    private var winner = mutableListOf<String>()
    private val comMenu = arrayOf("batu", "gunting", "kertas")

    open fun startGame(callback: Alerts, elPlayer: ImageView, playerChoice: String) {

        player1 = playerChoice
        println("Pemain 1 memilih ${playerChoice}")
        player2 = comMenu.random()
        println("COM memilih ${player2}")
        player1Element = elPlayer

        changeBackground(elPlayer, player2!!, R.color.bgSelected)

//        for (i in 1..2) {
//            when (i) {
//                1 -> {
//                    println("Masukkan pemain $i tjoy :")
//                    player1 = readLine()?.lowercase().toString()
//                }
//                2 -> {
//                    println("Masukkan pemain $i tjoy:")
//                    player2 = readLine()?.lowercase().toString()
//                }
//            }
//        }

//        println("DAN HASILNYA ADALAAAAHHHHH : ")
//
//        showCountDown(1)


        if (player1.equals(player2)) {
            message = "Imbang tjoy gak ada yang menang!"
            callback.dialogResult("Draw")
        } else if (player1 == "gunting" && player2 == "kertas" || player1 == "batu" && player2 == "gunting" || player1 == "kertas" && player2 == "batu") {
            message = "Pemain 1 menang tjoy, $player1 ngalahin $player2!"
            winner.add("Player 1 menang dengan stat game $player1 mengalahkan $player2 melawan Player 2")
            callback.dialogResult("playerOne")
        } else if (player2 == "gunting" && player1 == "kertas" || player2 == "batu" && player1 == "gunting" || player2 == "kertas" && player1 == "batu") {
            message = "Pemain 2 menang tjoy, $player2 ngalahin $player1!"
            winner.add("Player 2 menang dengan stat $player2 mengalahkan $player1 melawan Player 1")
            callback.dialogResult("COM")
        } else {
            message = "Salah masukin pilihan tjoy, coba lagi dong!"
        }

        callback.showAlertMessages(message)
    }

    open fun changeBackground(player: ImageView, com: String, color: Int) {
        background.resultBackground(
            player,
            com,
            ContextCompat.getColorStateList(background as Context, color)
        )
    }

    open fun resetGame(callback: Alerts) {
        background.resultBackground(
            player1Element,
            player2,
            ContextCompat.getColorStateList(background as Context, R.color.white)
        )

        player1 = null
        player2 = null
        player1Element = null
        message = ""
        winner.clear()
        callback.dialogResult("VS")
    }

    override fun showCountDownResult(result: String) {
        println(result)
    }
}