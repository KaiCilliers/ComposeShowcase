package com.example.composeshowcase.business

import com.example.composeshowcase.models.BookListItem
import com.example.composeshowcase.models.Resource
import kotlinx.coroutines.flow.Flow

interface FetchBookListContract {
    suspend operator fun invoke(): Flow<Resource<List<BookListItem>>>
}
