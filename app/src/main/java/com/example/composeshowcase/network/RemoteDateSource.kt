package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import com.example.composeshowcase.network.models.NetworkResult

/**
 * Concrete implementation of [RemoteDataSourceContract]
 */
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