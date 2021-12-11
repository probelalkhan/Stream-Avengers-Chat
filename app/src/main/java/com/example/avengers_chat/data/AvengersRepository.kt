package com.example.avengers_chat.data

import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class AvengersRepository(
    private val api: AvengersApi
): SafeApiCall {

    suspend fun getAvengers() : Resource<List<Avenger>> = safeApiCall { api.getAvengers() }

    fun connectUser(avenger: Avenger){
        disconnectUser(avenger)
        val user = User(
            id = avenger.id,
            extraData = mutableMapOf("name" to avenger.name)
        )
        ChatClient.instance().connectUser(user, avenger.token).enqueue()
    }

    private fun disconnectUser(avenger: Avenger){
        val user = ChatClient.instance().getCurrentUser()
        if(user != null && avenger.id == user.id){
            ChatClient.instance().disconnect()
        }
    }
}