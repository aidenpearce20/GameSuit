package com.example.game_suit.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(var name: String, var choice: String) : Parcelable

