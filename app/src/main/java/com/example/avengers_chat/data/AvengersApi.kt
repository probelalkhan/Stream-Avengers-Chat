package com.example.avengers_chat.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AvengersApi {

    @GET("Avengers.json")
    suspend fun getAvengers(): List<Avenger>

    companion object{
        operator fun invoke(): AvengersApi{
            return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/skydoves/933a70b21d7c96e8a8fdbe31ca72dada/raw/e6c9c7a5b6932dc1c403cc2405f406f3f57e1f56/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AvengersApi::class.java)
        }
    }
}