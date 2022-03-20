package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.NetworkResult
import retrofit2.Response

interface SafeApiCallContract {
    suspend fun <T> safeApiCall(request: suspend () -> Response<T>): NetworkResult<T>
}
