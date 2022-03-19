package com.example.composeshowcase.models

import java.lang.Exception

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val msg: String?, val exception: Exception?) : NetworkResult<Nothing>()
    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(message: String? = null, exception: Exception? = null) = Error(message, exception)
    }
}
