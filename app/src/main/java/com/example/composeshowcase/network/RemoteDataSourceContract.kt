package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import com.example.composeshowcase.models.NetworkResult

// todo explain purpose
//  document etc
//  expose business models (for now just network models)
// todo create business models for separation of concerns
interface RemoteDataSourceContract {
    suspend fun bookList(): NetworkResult<List<BookListItemResponse>>
    suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse>
}