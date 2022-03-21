package com.example.composeshowcase.business

import com.example.composeshowcase.business.models.BookListItem
import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection
import com.example.composeshowcase.repository.BookRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Concrete implementation of [FetchBookListContract]
 */
class FetchBookListUseCase(
    private val repo: BookRepositoryContract
) : FetchBookListContract {
    override suspend fun invoke(
        sortBy: Sort,
        sortDirection: SortDirection
    ): Flow<Resource<List<BookListItem>>> = flow {
        repo.allBooks().collect {
            when (it) {
                is Resource.Error -> emit(it)
                Resource.Loading -> emit(it)
                is Resource.Success -> {
                    val sortedList = if (sortDirection == SortDirection.Descending) {
                        it.data.sortedByDescending { listItem ->
                            foo(listItem, sortBy)
                        }
                    } else {
                        it.data.sortedBy { listItem ->
                            foo(listItem, sortBy)
                        }
                    }
                    emit(Resource.success(sortedList))
                }
            }
        }
    }

    private fun foo(item: BookListItem, sortBy: Sort): String {
        return when (sortBy) {
            Sort.Author -> item.author
            Sort.Currency -> item.currencyCode
            Sort.Name -> item.title
            Sort.Price -> item.price.toString()
        }
    }
}