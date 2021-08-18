package com.blank.game_suit.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.game_suit.R

class LandingThird : Fragment(R.layout.fragment_landing_third) {

    private var listener: ((Boolean) -> Unit?)? = null
    private var listenerName: ((String) -> Unit?)? = null

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        // Initialize Interface in Fragment
//        if (context is PassingData) {
//            passData = context
//        } else {
//            throw RuntimeException(requireContext().toString() + " must implement PassingData Interface")
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextPersonName by lazy { view.findViewById<EditText>(R.id.editTextPersonName) }

        editTextPersonName.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
               listener!!(true)
                listenerName!!(it.toString())
            } else {
                listener!!(false)
                listenerName!!("")
            }
        }

    }

    companion object{
        fun newInstance(listener: ((Boolean) -> Unit), listenerName: ((String) -> Unit)): LandingThird {
            val landingThird = LandingThird()
            landingThird.listener = listener
            landingThird.listenerName = listenerName
            return landingThird
        }
    }

}