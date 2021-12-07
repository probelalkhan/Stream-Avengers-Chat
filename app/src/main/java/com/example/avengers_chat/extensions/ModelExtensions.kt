package com.example.avengers_chat.extensions

import android.graphics.Color
import com.example.avengers_chat.data.Avenger

val Avenger.parsedColor: Int
    inline get() = Color.parseColor(color)

