package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import retrofit2.Response

interface BookServiceContract {
    /**
     * Get list of books
     * @return [Response] of a list of book items
     */
    suspend fun bookList(): Response<List<BookListItemResponse>>

    /**
     * Get book details based on id
     * @param id uniquely identifies a book
     * @return [Response] of a book detail
     */
    suspend fun bookDetail(id: Int): Response<BookDetailResponse>
}