package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListResponse
import retrofit2.Response

interface BookServiceContract {
    suspend fun bookList(): Response<BookListResponse>
    suspend fun bookDetail(id: Int): Response<BookDetailResponse>
}