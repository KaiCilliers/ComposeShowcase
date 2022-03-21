package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService : BookServiceContract {
    /**
     * Get list of books
     * @return [Response] of a list of book items
     */
    @GET("/books")
    override suspend fun bookList(): Response<List<BookListItemResponse>>

    /**
     * Get book details based on id
     * @param id uniquely identifies a book
     * @return [Response] of a book detail
     */
    @GET("/book/{id}")
    override suspend fun bookDetail(@Path("id") id: Int): Response<BookDetailResponse>
}