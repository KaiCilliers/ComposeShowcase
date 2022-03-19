package com.example.composeshowcase.business

import com.example.composeshowcase.models.BookListItem
import com.example.composeshowcase.repository.BookRepositoryContract
import kotlinx.coroutines.flow.Flow

// todo have usecases return UI models?
class FetchBookListUseCase(
    private val repo: BookRepositoryContract
) : FetchBookListContract {
    override suspend fun invoke(): Flow<Resource<List<BookListItem>>> = repo.allBooks()
}