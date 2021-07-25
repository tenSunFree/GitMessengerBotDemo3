package com.example.gitmessengerbotdemo3.common.remote

sealed class BaseResponse {
    data class Loading(val loading: String = "") : BaseResponse()
    data class Error(val exception: Exception) : BaseResponse()
    data class Success<T>(val response: T) : BaseResponse()
}
