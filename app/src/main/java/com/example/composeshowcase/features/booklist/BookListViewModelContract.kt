package com.example.composeshowcase.features.booklist

import androidx.compose.runtime.State

interface BookListViewModelContract {
    val booksState: State<BooksState>
}