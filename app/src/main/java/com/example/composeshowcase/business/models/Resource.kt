package com.example.composeshowcase.business.models

/**
 * Model representing the current state of a business model
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val exception: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
    companion object {
        fun <T>success(data: T) = Success(data)
        fun error(exception: Exception) = Error(exception)
        fun error(message: String) = error(Exception(message))
        fun loading() = Loading
    }
}