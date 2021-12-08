package com.example.avengers_chat.extensions

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun Context.drawable(@DrawableRes resource: Int): Drawable {
    return ResourcesCompat.getDrawable(resources, resource, null)!!
}

fun <I : Parcelable> Intent.getParcelableOrThrow(key: String): I {
    return getParcelableExtra(key) as I?
        ?: throw IllegalArgumentException("$key not found")
}