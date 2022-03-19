package com.example.composeshowcase.repository

import com.example.composeshowcase.business.Resource
import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.BookListItem
import kotlinx.coroutines.flow.Flow

interface BookRepositoryContract {
    suspend fun allBooks(): Flow<Resource<List<BookListItem>>>
    // todo replace with Resource to indicate Loading, Failed, Success states
    suspend fun book(id: Int): Book?
}