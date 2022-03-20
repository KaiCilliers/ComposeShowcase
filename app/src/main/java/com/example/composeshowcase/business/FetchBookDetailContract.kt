package com.example.composeshowcase.business

import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.Resource
import kotlinx.coroutines.flow.Flow

interface FetchBookDetailContract {
    /**
     * Use case to fetch details of a book
     * @param id the id of the book to fetch
     * @return [Flow] of [Resource] to indicate current state of [Book]
     */
    suspend fun bookDetail(id: Int): Flow<Resource<Book>>
}