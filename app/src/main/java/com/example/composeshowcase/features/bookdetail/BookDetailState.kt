package com.example.composeshowcase.features.bookdetail

import com.example.composeshowcase.models.BookUI

sealed class BookDetailState {
    object Loading : BookDetailState()
    data class Success(val data: BookUI) : BookDetailState()
    data class Error(val exception: Exception) : BookDetailState()

    companion object {
        fun loading() = Loading
        fun success(data: BookUI) = Success(data)
        fun error(errorMessage: String) = Error(Exception(errorMessage))
    }
}