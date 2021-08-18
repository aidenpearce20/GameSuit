package com.blank.game_suit.classes

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.example.game_suit.R
import com.blank.game_suit.interfaces.Alerts
import com.blank.game_suit.interfaces.Background

open class LogicGame(private val background: Background) {
    private var player1: String? = null
    private var player2: String? = null
    private var player1Element: ImageView? = null
    private var playerChoiceSecond: String = ""
    private var playerChoiceImage: ImageView? = null
    private lateinit var message: String
    private var winner = mutableListOf<String>()
    private val comMenu = arrayOf("Batu", "Gunting", "Kertas")

    open fun startGame(
        callback: Alerts,
        username: String,
        gamePlay: String,
        elPlayer: ImageView,
        playerChoice: String,
        secondPlayerImage: ImageView? = null,
        secondPlayerChoice: String? = "",
        context: Context?
    ) {

        player1 = playerChoice
        println("Pemain 1 memilih $playerChoice")
        player2 =
            if (secondPlayerChoice!!.isNotEmpty()) secondPlayerChoice.dropLast(1) else comMenu.random()
        val termName = if (gamePlay == "player") "Pemain 2" else "CPU"
        println("$termName memilih $player2")
        player1Element = elPlayer

        changeBackground(
            elPlayer,
            player2!!,
            if (secondPlayerChoice.isNotEmpty()) secondPlayerImage else null,
            if (secondPlayerChoice.isNotEmpty()) secondPlayerChoice else "",
            R.color.bgSelected
        )

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
            callback.dialogResult("Draw", "Draw")
        } else if (player1 == "Gunting" && player2 == "Kertas" || player1 == "Batu" && player2 == "Gunting" || player1 == "Kertas" && player2 == "Batu") {
            message = "$username menang tjoy, $player1 ngalahin $player2!"
            winner.add("$username menang dengan stat game $player1 mengalahkan $player2 melawan $termName")
            callback.dialogResult("playerOne", username)
        } else if (player2 == "Gunting" && player1 == "Kertas" || player2 == "Batu" && player1 == "Gunting" || player2 == "Kertas" && player1 == "Batu") {
            message = "$termName menang tjoy, $player2 ngalahin $player1!"
            winner.add("$termName menang dengan stat $player2 mengalahkan $player1 melawan $username")
            callback.dialogResult("COM", termName)
        } else {
            message = "Salah masukin pilihan tjoy, coba lagi dong!"
        }

        Toast.makeText(context, "$termName Memilih $player2", Toast.LENGTH_LONG).show()


        callback.showAlertMessages(message)
    }

    open fun changeBackground(
        player: ImageView,
        com: String,
        scndPlayerImg: ImageView?,
        scndPlayerChc: String?,
        color: Int
    ) {
        background.resultBackground(
            player,
            com,
            scndPlayerImg,
            scndPlayerChc,
            color
        )
    }

    open fun resetGame(callback: Alerts) {
        background.resultBackground(
            player1Element,
            player2,
            if (playerChoiceSecond.isNotEmpty()) playerChoiceImage else null,
            if (playerChoiceSecond.isNotEmpty()) playerChoiceSecond else "",
            R.color.bg_activity
        )

        player1 = null
        player2 = null
        player1Element = null
        message = ""
        winner.clear()
//        callback.dialogResult("VS", "")
    }

//    override fun showCountDownResult(result: String) {
//        println(result)
//    }
}