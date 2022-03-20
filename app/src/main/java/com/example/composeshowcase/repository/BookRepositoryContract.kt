package com.example.composeshowcase.repository

import com.example.composeshowcase.models.Resource
import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.BookListItem
import kotlinx.coroutines.flow.Flow

interface BookRepositoryContract {
    suspend fun allBooks(): Flow<Resource<List<BookListItem>>>
    suspend fun book(id: Int): Flow<Resource<Book>>
}