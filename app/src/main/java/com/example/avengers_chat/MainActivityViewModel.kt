package com.example.avengers_chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avengers_chat.data.Avenger
import com.example.avengers_chat.data.AvengersRepository
import com.example.avengers_chat.data.Resource
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: AvengersRepository
) : ViewModel() {

    private val _avengers = MutableLiveData<Resource<List<Avenger>>>()
    val avengers: LiveData<Resource<List<Avenger>>>
        get() = _avengers

    init {
        getAvengers()
    }

    private fun getAvengers() = viewModelScope.launch {
        _avengers.value = Resource.Loading
        _avengers.value = repository.getAvengers()
    }
}