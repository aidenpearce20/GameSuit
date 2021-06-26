import kotlin.system.exitProcess

class Main : LogicGame() {
    companion object : Alerts {
        @JvmStatic
        fun main(args: Array<String>) {
            var choice: Int? = 0

            println("// ========================== //")
            println("GAME SUIT TERMINAL VERSION")
            println("// ========================== //")
            println("PILIH MENU TJOY : ")
            println("// ========================== //")
            println("1. Gasss teruuss")
            println("// ========================== //")
            println("2. Cabut aja")
            println("// ========================== //")
            println("Pilih yang mana tjoy? : ")

            choice = readLine()?.toInt() ?: 0

            when(choice) {
                1 -> {
                    var control = LogicGame()
                    control.startGame(this)
                }
                2 -> {
                    println("Gak jadi maen tjoy")
                    exitProcess(0)
                }
                else -> {
                    println("Salah pilih tjoy, Coba lagi!!!!!!!")
                }
            }

        }

        override fun showAlertMessages(text: String) {
            println(text)
        }
    }

}

