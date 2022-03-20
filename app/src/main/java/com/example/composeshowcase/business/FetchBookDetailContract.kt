package com.example.composeshowcase.business

import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.Resource
import kotlinx.coroutines.flow.Flow

interface FetchBookDetailContract {
    suspend fun bookDetail(id: Int): Flow<Resource<Book>>
}