package com.example.avengers_chat.data

class AvengersRepository(
    private val api: AvengersApi
): SafeApiCall {
    suspend fun getAvengers() : Resource<List<Avenger>> = safeApiCall { api.getAvengers() }
}