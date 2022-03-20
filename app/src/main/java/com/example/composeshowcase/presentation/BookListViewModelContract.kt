package com.example.composeshowcase.presentation

import androidx.compose.runtime.State
import com.example.composeshowcase.models.BooksState

interface BookListViewModelContract {
    val booksState: State<BooksState>
}