package com.example.composeshowcase.repository

import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.BookListItem
import kotlinx.coroutines.flow.Flow

interface BookRepositoryContract {
    suspend fun allBooks(): Flow<Resource<List<BookListItem>>>
    suspend fun book(id: Int): Flow<Resource<Book>>
}