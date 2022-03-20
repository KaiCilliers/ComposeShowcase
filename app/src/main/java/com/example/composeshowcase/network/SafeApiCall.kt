package com.example.composeshowcase.network

import com.example.composeshowcase.network.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class SafeApiCall(private val dispatcher: CoroutineDispatcher = Dispatchers.IO): SafeApiCallContract {
    override suspend fun <T> safeApiCall(request: suspend () -> Response<T>): NetworkResult<T> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = request()
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext NetworkResult.success(it)
                    }
                }
                NetworkResult.error("Code: ${response.code()} \n ${response.errorBody()}")
            } catch (e: Exception) {
                NetworkResult.error(e.message, e)
            }
        }
    }
}