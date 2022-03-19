package com.example.composeshowcase.repository

import com.example.composeshowcase.extensions.toBusinessModel
import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.BookListItem
import com.example.composeshowcase.models.NetworkResult
import com.example.composeshowcase.network.RemoteDataSourceContract

class BookRepository(
    private val remote: RemoteDataSourceContract
) : BookRepositoryContract {
    override suspend fun allBooks(): List<BookListItem> {
        return with(remote.bookList()) {
            when (this) {
                is NetworkResult.Error -> return@with emptyList()
                is NetworkResult.Success -> return@with data.map { it.toBusinessModel() }
            }
        }
    }

    override suspend fun book(id: Int): Book? {
        return with(remote.bookDetail(id)) {
            when (this) {
               is NetworkResult.Error -> return@with null
                is NetworkResult.Success -> return@with data.toBusinessModel()
            }
        }
    }
}