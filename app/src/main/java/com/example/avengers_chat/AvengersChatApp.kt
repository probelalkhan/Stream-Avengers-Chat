package com.example.avengers_chat

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.livedata.ChatDomain

class AvengersChatApp : Application(){

    override fun onCreate() {
        super.onCreate()
        val chatClient: ChatClient =
            ChatClient.Builder(
                applicationContext.getString(R.string.stream_api_key),
                applicationContext
            ).build()
        ChatDomain.Builder(chatClient, applicationContext)
            .offlineEnabled()
            .build()
    }
}