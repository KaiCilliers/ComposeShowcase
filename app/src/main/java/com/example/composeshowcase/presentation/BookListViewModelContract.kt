package com.example.composeshowcase.presentation

import androidx.compose.runtime.State

interface BookListViewModelContract {
    val booksState: State<BooksState>
}