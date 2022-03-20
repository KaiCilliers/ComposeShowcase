package com.example.composeshowcase.business

import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.Resource
import kotlinx.coroutines.flow.Flow

interface FetchBookDetailContract {
    suspend fun bookDetail(id: Int): Flow<Resource<Book>>
}