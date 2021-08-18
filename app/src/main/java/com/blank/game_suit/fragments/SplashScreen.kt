package com.blank.game_suit.fragments

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.game_suit.R
import com.blank.game_suit.classes.replaceFragmentFromActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class SplashScreen(private val fragContainer: Int) : Fragment(R.layout.fragment_splash_screen) {

    private lateinit var headerImage: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headerImage = view.findViewById(R.id.headerImage)

        // Dexter Permissions
        Dexter.withContext(context)
            .withPermission(Manifest.permission.INTERNET)
            .withListener(object : PermissionListener {

                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    Glide.with(context!!).load("https://i.ibb.co/HC5ZPgD/splash-screen1.png").into(headerImage)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(context, "ERROR BOSS GAK BISA LOAD GAMBAR", Toast.LENGTH_SHORT).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            }).check()

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler(Looper.myLooper()!!).postDelayed({
            replaceFragmentFromActivity(parentFragmentManager, ViewPagerFragment(fragContainer), fragContainer)
        }, 1500)
    }

}