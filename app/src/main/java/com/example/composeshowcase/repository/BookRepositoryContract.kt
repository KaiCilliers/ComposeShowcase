package com.example.composeshowcase.repository

import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.BookListItem
import kotlinx.coroutines.flow.Flow

/**
 * Source of truth to get information on books
 */
interface BookRepositoryContract {
    /**
     * Get a list of books
     * @return [Flow] of [Resource] representing the state of list of books
     */
    suspend fun allBooks(): Flow<Resource<List<BookListItem>>>

    /**
     * Get details about a specific book
     * @param id uniquely identifies a book
     * @return [Flow] of [Resource] representing the state of a single book
     */
    suspend fun book(id: Int): Flow<Resource<Book>>
}