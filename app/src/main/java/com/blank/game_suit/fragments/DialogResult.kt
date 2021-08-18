package com.blank.game_suit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.game_suit.R

class DialogResult : DialogFragment(R.layout.fragment_dialog_result) {
    lateinit var winnerName: String

    private lateinit var winner: TextView
    private lateinit var btnReplay: Button
    private lateinit var btnMenu: Button

    private var backToMenu: ((Boolean) -> Unit?)? = null
    private var replayGame: ((Boolean) -> Unit?)? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        winner = view.findViewById(R.id.tvDialogResult)
        btnReplay = view.findViewById(R.id.btnReplay)
        btnMenu = view.findViewById(R.id.btnMenu)

        winner.text = if (winnerName == "Draw") "SERI!" else "$winnerName\nMENANG!"


        btnReplay.setOnClickListener {
            replayGame!!(true)
            dialog?.dismiss()
        }

        btnMenu.setOnClickListener {
            backToMenu!!(true)
            dialog?.dismiss()
        }


    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object{
        fun newInstance(winnerName:String ,backToMenu: ((Boolean) -> Unit), replayGame: ((Boolean) -> Unit)): DialogResult {
            val dialogResult = DialogResult()
            dialogResult.winnerName = winnerName
            dialogResult.backToMenu = backToMenu
            dialogResult.replayGame = replayGame
            return dialogResult
        }
    }

}