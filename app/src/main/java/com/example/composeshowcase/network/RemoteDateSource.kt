package com.example.composeshowcase.network

import com.example.composeshowcase.models.BookDetailResponse
import com.example.composeshowcase.models.BookListItemResponse
import com.example.composeshowcase.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class RemoteDateSource(
    private val api: BookServiceContract,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSourceContract {
    override suspend fun bookList(): NetworkResult<List<BookListItemResponse>> {
        return safeApiCall { api.bookList() }
    }

    override suspend fun bookDetail(id: Int): NetworkResult<BookDetailResponse> {
        return safeApiCall { api.bookDetail(id) }
    }

    // TODO clean up and update with clear error messages
    //  move to delegate
    private suspend fun <T> safeApiCall(request: suspend () -> Response<T>): NetworkResult<T> {
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