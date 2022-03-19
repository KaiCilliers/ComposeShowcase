package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import retrofit2.Response

interface BookServiceContract {
    suspend fun bookList(): Response<List<BookListItemResponse>>
    suspend fun bookDetail(id: Int): Response<BookDetailResponse>
}