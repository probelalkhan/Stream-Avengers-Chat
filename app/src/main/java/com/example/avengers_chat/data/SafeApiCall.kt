package com.example.avengers_chat.data

import retrofit2.HttpException

interface SafeApiCall {

    suspend fun<T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return try{
            Resource.Success(apiCall())
        }catch (e: Exception){
            if(e is HttpException) {
                Resource.Failure(e.code(), e.response()?.errorBody())
            }else{
                Resource.Failure(411, null)
            }
        }
    }

}