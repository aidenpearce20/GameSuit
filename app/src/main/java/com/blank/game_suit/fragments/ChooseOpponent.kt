package com.blank.game_suit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.game_suit.R
import com.blank.game_suit.classes.replaceFragmentFromActivity
import com.blank.game_suit.data.UserData
import com.google.android.material.snackbar.Snackbar

class ChooseOpponent(private val fragContainer: Int, private var showSnackbar: Boolean = true) : Fragment(R.layout.fragment_choose_opponent) {
    private lateinit var bundle: Bundle
    private lateinit var username: String
    private lateinit var snackbar: Snackbar
    private lateinit var imgPlayer: ImageView
    private lateinit var txtName: TextView
    private lateinit var imgComp: ImageView
    private lateinit var txtComp: TextView

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgPlayer = view.findViewById(R.id.imgPlayer)
        txtName = view.findViewById(R.id.tvPlayer)
        imgComp = view.findViewById(R.id.imgComp)
        txtComp = view.findViewById(R.id.tvComp)

        val dataView = arrayListOf(imgPlayer, imgComp)

        bundle = requireArguments()
        val dataUser = bundle.getParcelable<UserData>("key")
        username = dataUser!!.name
        txtName.text = "$username VS Pemain"
        txtComp.text = "$username VS CPU"

        snackbar =  Snackbar.make(view, "Selamat Datang $username", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }
        snackbar.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.white))
        snackbar.setActionTextColor(ContextCompat.getColorStateList(requireContext(), R.color.snackDismiss))
        snackbar.setBackgroundTint(R.color.black_80)
        snackbar.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.black_80))

        if(showSnackbar) snackbar.show()


        dataView.forEach{ it ->
            it.setOnClickListener {
                choiceGame(it, username)
            }
        }
        
    }

    private fun choiceGame(v: View, name: String) {
        lateinit var userData: UserData
        snackbar.dismiss()
        when(v.tag) {
            "imgPlayer" -> {
                userData = UserData(name, "player")
                replaceFragmentFromActivity(parentFragmentManager, GameStart(fragContainer), fragContainer, userData)
            }
            "imgComp" -> {
                userData = UserData(name, "cpu")
                replaceFragmentFromActivity(parentFragmentManager, GameStart(fragContainer), fragContainer, userData)
            }
        }
    }
}