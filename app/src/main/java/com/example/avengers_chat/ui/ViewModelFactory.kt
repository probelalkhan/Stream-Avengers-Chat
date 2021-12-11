package com.example.avengers_chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avengers_chat.data.AvengersRepository

class ViewModelFactory(
    private val repository: AvengersRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java)
            -> MainActivityViewModel(repository)
            modelClass.isAssignableFrom(AvengersChannelListViewModel::class.java)
            -> AvengersChannelListViewModel(repository)
            else -> throw IllegalArgumentException("Cannot create ViewModel")
        }
        return viewModel as T
    }
}