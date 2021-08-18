package com.blank.game_suit.classes

abstract class CountDown {

    fun showCountDown(counter: Int) {
        var i = counter
        while (i < 4) {
            when (i) {
                1 -> showCountDownResult("Sabar")
                2 -> showCountDownResult("Tahan")
                3 -> showCountDownResult("Dikit lagi")
            }
            i++
            Thread.sleep(1000)
        }
    }

    abstract fun showCountDownResult(result: String)
}