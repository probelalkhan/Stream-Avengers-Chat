package com.example.avengers_chat.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.findViewTreeLifecycleOwner
import coil.imageLoader
import coil.request.ImageRequest
import com.skydoves.androidveil.VeilLayout

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

