open class LogicGame : CountDown() {
    private var player1: String? = null
    private var player2: String? = null
    protected open lateinit var message: String
    internal var winner = mutableListOf<String>()

    open fun startGame(callback: Alerts) {

        for (i in 1..2) {
            when (i) {
                1 -> {
                    println("Masukkan pemain $i tjoy :")
                    player1 = readLine()?.lowercase().toString()
                }
                2 -> {
                    println("Masukkan pemain $i tjoy:")
                    player2 = readLine()?.lowercase().toString()
                }
            }
        }

        println("DAN HASILNYA ADALAAAAHHHHH : ")

        showCountDown(1)


        if (player1.equals(player2)) {
            message = "Imbang tjoy gak ada yang menang!"
        } else if (player1 == "gunting" && player2 == "kertas" || player1 == "batu" && player2 == "gunting" || player1 == "kertas" && player2 == "batu") {
            message = "Pemain 1 menang tjoy, $player1 ngalahin $player2!"
            do {
                winner.clear()
            } while (winner.size != 0)
            winner.add("Player 1 menang dengan stat game $player1 mengalahkan $player2 melawan Player 2")
        } else if (player2 == "gunting" && player1 == "kertas" || player2 == "batu" && player1 == "gunting" || player2 == "kertas" && player1 == "batu") {
            message = "Pemain 2 menang tjoy, $player2 ngalahin $player1!"
            do {
                winner.clear()
            } while (winner.size != 0)
            winner.add("Player 2 menang dengan stat $player2 mengalahkan $player1 melawan Player 1")
        } else {
            message = "Salah masukin pilihan tjoy, coba lagi dong!"
        }

        callback.showAlertMessages(message)
    }

    override fun showCountDownResult(result: String) {
        println(result)
    }
}