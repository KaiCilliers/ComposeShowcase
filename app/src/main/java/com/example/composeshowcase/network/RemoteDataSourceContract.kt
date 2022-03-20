package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import com.example.composeshowcase.models.NetworkResult

// todo write docs
interface RemoteDataSourceContract {
    suspend fun bookList(): NetworkResult<List<BookListItemResponse>>
    suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse>
}