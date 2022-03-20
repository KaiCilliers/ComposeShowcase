package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import com.example.composeshowcase.network.models.NetworkResult

// todo write docs
interface RemoteDataSourceContract {
    suspend fun bookList(): NetworkResult<List<BookListItemResponse>>
    suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse>
}