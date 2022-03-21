package com.example.composeshowcase.business

import com.example.composeshowcase.business.models.BookListItem
import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection
import kotlinx.coroutines.flow.Flow

interface FetchBookListContract {
    /**
     * Use case to list of books
     * @return [Flow] of [Resource] to indicate current state of [BookListItem]
     */
    suspend operator fun invoke(
        sortBy: Sort = Sort.Price,
        sortDirection: SortDirection = SortDirection.Descending
    ): Flow<Resource<List<BookListItem>>>
}
