package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import com.example.composeshowcase.network.models.NetworkResult
import retrofit2.Response

interface RemoteDataSourceContract {
    /**
     * Get list of books
     * @return [NetworkResult] of a list of book items
     */
    suspend fun bookList(): NetworkResult<List<BookListItemResponse>>

    /**
     * Get book details based on id
     * @param id uniquely identifies a book
     * @return [NetworkResult] of a book detail
     */
    suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse>
}