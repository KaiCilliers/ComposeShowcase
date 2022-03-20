package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import com.example.composeshowcase.models.NetworkResult

class RemoteDateSource(
    private val api: BookServiceContract,
    safeApiCall: SafeApiCallContract = SafeApiCall()
) : RemoteDataSourceContract, SafeApiCallContract by safeApiCall {
    override suspend fun bookList(): NetworkResult<List<BookListItemResponse>> {
        return safeApiCall { api.bookList() }
    }

    override suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse> {
        return safeApiCall { api.bookDetail(id) }
    }
}