package com.example.composeshowcase.business

import com.example.composeshowcase.models.Book
import com.example.composeshowcase.models.Resource
import com.example.composeshowcase.repository.BookRepositoryContract
import kotlinx.coroutines.flow.Flow

class FetchBookDetailUseCase(
    private val repo: BookRepositoryContract
) : FetchBookDetailContract {
    override suspend fun bookDetail(id: Int): Flow<Resource<Book>> = repo.book(id)
}