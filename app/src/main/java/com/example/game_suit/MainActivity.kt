package com.example.game_suit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.game_suit.classes.addFragmentToActivity
import com.example.game_suit.fragments.ChooseOpponent
import com.example.game_suit.fragments.SplashScreen


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContentView(R.layout.activity_main)

        addFragmentToActivity(supportFragmentManager, SplashScreen(R.id.fragContainer), R.id.fragContainer)

    }
}