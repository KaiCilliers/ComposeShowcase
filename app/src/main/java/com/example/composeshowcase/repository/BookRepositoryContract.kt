package com.example.composeshowcase.repository

import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.BookListItem

interface BookRepositoryContract {
    suspend fun allBooks(): List<BookListItem>
    // todo replace with Resource to indicate Loading, Failed, Success states
    suspend fun book(id: Int): Book?
}