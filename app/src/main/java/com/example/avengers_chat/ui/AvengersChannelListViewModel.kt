package com.example.avengers_chat.ui

import androidx.lifecycle.ViewModel
import com.example.avengers_chat.data.Avenger
import com.example.avengers_chat.data.AvengersRepository

class AvengersChannelListViewModel(
    private val repository: AvengersRepository
): ViewModel() {

    fun connectUser(avenger: Avenger) = repository.connectUser(avenger)
}