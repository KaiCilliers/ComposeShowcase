package com.example.composeshowcase.features.booklist

import androidx.compose.runtime.State

interface BookListViewModelContract {
    /**
     * Represent the state of the books list on home screen view
     */
    val booksState: State<BooksState>

    /**
     * Fetch list of books
     */
    fun fetchBookList()
}