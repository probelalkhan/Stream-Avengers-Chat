package com.example.avengers_chat.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun Context.drawable(@DrawableRes resource: Int): Drawable {
    return ResourcesCompat.getDrawable(resources, resource, null)!!
}