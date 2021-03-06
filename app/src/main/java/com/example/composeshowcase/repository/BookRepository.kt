package com.example.composeshowcase.repository

import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.extensions.toBusinessModel
import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.BookListItem
import com.example.composeshowcase.network.models.NetworkResult
import com.example.composeshowcase.network.RemoteDataSourceContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Concrete implementation of [BookRepositoryContract]
 */
class BookRepository(
    private val remote: RemoteDataSourceContract
) : BookRepositoryContract {
    override suspend fun allBooks(): Flow<Resource<List<BookListItem>>> {
        return flow {
            emit(Resource.loading())
            with(remote.bookList()) {
                emit(when (this) {
                    is NetworkResult.Error -> Resource.error(this.exception?.message ?: this.msg.orEmpty())
                    is NetworkResult.Success -> Resource.success(data.map { it.toBusinessModel() })
                })
            }
        }
    }

    override suspend fun book(id: Int): Flow<Resource<Book>> {
        return flow {
            emit(Resource.loading())
            emit(with(remote.bookDetail(id)) {
               when(this) {
                   is NetworkResult.Error -> Resource.error(this.exception?.message ?: this.msg.orEmpty())
                   is NetworkResult.Success -> Resource.success(data.toBusinessModel())
               }
            })
        }
    }
}