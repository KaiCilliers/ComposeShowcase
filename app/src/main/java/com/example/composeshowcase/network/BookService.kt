package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService : BookServiceContract {
    @GET("/books")
    override suspend fun bookList(): Response<List<BookListItemResponse>>

    @GET("/book/{id}")
    override suspend fun bookDetail(@Path("id") id: Int): Response<BookDetailResponse>
}