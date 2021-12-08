package com.example.avengers_chat.data

import android.os.Parcelable
import io.getstream.chat.android.client.ChatClient
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avenger(
    val id: String,
    val name: String,
    val token: String,
    val color: String,
    val quote: String,
    val video: String,
    val livecid: String,
    val poster: String
) : Parcelable{
    fun getProfileImage(): String? {
//        val currentUser = ChatClient.instance().getCurrentUser()
//        if (id == currentUser?.id) {
//            return currentUser.extraData["image"] as? String
//        }
        return "https://getstream.imgix.net/images/random_svg/${name.first().uppercase()}.png"
    }
}