package com.example.avengers_chat.base

import androidx.appcompat.app.AppCompatActivity
import com.example.avengers_chat.MainActivityViewModelFactory
import com.example.avengers_chat.data.AvengersApi
import com.example.avengers_chat.data.AvengersRepository

abstract class BaseActivity: AppCompatActivity() {

    private val api = AvengersApi()
    private val repository = AvengersRepository(api)
    protected val factory = MainActivityViewModelFactory(repository)

}