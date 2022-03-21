package com.example.composeshowcase.features.booklist

import androidx.compose.runtime.State
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection

interface BookListViewModelContract {
    /**
     * Represent the state of the books list on home screen view
     */
    val booksState: State<BooksState>

    /**
     * Fetch list of books
     */
    fun fetchBookList(
        sort: Sort = Sort.Name,
        sortDirection: SortDirection = SortDirection.Descending
    )
    fun reorderList(
        sort: Sort = Sort.Name,
        sortDirection: SortDirection = SortDirection.Descending
    )
}