package com.example.composeshowcase.features.bookdetail

import androidx.compose.runtime.State

interface BookDetailViewModelContract {
    /**
     * Represent the state of book detail screen
     */
    val bookState: State<BookDetailState>

    /**
     * Fetch details of book based on id
     * @param id uniquely identifies a book
     */
    fun fetchBookDetail(id: Int = 100)
}

