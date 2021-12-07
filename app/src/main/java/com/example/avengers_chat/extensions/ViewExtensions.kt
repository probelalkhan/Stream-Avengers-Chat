package com.example.avengers_chat.extensions

import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.skydoves.androidveil.VeilLayout
import kotlin.math.hypot

val RecyclerView.ViewHolder.adapterPositionOrNull: Int?
    inline get() = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }

fun View.bindStatusBarColor(color: Int) {
    val mContext = context
    if (mContext is Activity) {
        val window = mContext.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }
}

fun View.startCircularReveal(@ColorInt color: Int) {
    val cx = 0
    val cy = left
    val finalRadius = hypot(width.toDouble(), height.toDouble())
    if (isAttachedToWindow) {
        ViewAnimationUtils.createCircularReveal(
            this, cx, cy, 0f, finalRadius.toFloat()
        ).apply {
            DrawableCompat.setTint(background, color)
            isVisible = true
            duration = 450
            start()
        }
    }
}

fun AppCompatImageView.loadAndUnveil(poster: String, veilLayout: VeilLayout){
    val request = ImageRequest.Builder(context)
        .data(poster)
        .target(this)
        .lifecycle(this.findViewTreeLifecycleOwner())
        .listener(
            onCancel = {
                veilLayout.unVeil()
            },
            onError = {request, throwable ->
                veilLayout.unVeil()
            },
            onSuccess = {request, metadata ->
                veilLayout.unVeil()
            }
        ).build()
    this.context.imageLoader.enqueue(request)
}