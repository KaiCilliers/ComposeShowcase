package com.example.composeshowcase.features.booklist

/**
 * Represent the state of the home screen
 */
sealed class BooksState {
    object Loading : BooksState()
    data class Success(val data: List<BookListItemUI>) : BooksState()
    data class Error(val exception: Exception) : BooksState()

    companion object {
        fun loading() = Loading
        fun success(data: List<BookListItemUI>) = Success(data)
        fun error(errorMessage: String) = Error(Exception(errorMessage))
    }
}