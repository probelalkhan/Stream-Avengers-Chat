package com.example.avengers_chat.data

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Failure(val errorCode: Int, val errorBody: ResponseBody?): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}