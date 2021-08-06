package com.example.game_suit.classes

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.game_suit.R

private val args = Bundle()


fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment?, frameId: Int) {
    val transaction: FragmentTransaction = manager.beginTransaction()
    transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
    transaction.add(frameId, fragment!!)
    transaction.commit()
}

fun replaceFragmentFromActivity(manager: FragmentManager, fragment: Fragment?, frameId: Int, value: Parcelable? = null) {
    val transaction: FragmentTransaction = manager.beginTransaction()
    args.putParcelable("key", value)
    fragment!!.arguments = args
    transaction.addToBackStack(null)
    transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
    transaction.replace(frameId, fragment)
    transaction.commit()
}