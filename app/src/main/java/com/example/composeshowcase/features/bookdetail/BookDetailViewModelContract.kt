package com.example.composeshowcase.features.bookdetail

import androidx.compose.runtime.State

interface BookDetailViewModelContract {
    val bookState: State<BookDetailState>
    fun fetchBookDetail(id: Int = 100)
}

