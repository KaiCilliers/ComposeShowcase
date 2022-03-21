package com.example.composeshowcase.features.booklist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.composeshowcase.business.FetchBookListUseCase
import com.example.composeshowcase.business.models.Resource
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection
import com.example.composeshowcase.extensions.toUidModel
import com.example.composeshowcase.network.Network
import com.example.composeshowcase.network.RemoteDateSource
import com.example.composeshowcase.repository.BookRepository
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel(), BookListViewModelContract {

    private val booksUseCase by lazy {
        FetchBookListUseCase(
            BookRepository(
                RemoteDateSource(Network.bookService)
            )
        )
    }

    override val booksState: MutableState<BooksState> = mutableStateOf(BooksState.loading())
    private val allBooks: MutableList<BookListItemUI> = mutableListOf()

    init {
        fetchBookList()
    }

    override fun fetchBookList(sort: Sort, sortDirection: SortDirection) {
        viewModelScope.launch {
            booksUseCase(sort, sortDirection).collect { resource ->
                booksState.value = when(resource) {
                    is Resource.Success -> {
                        val books = resource.data.map { it.toUidModel() }.also {
                            allBooks.clear()
                            allBooks.addAll(it)
                        }
                        BooksState.success(books)
                    }
                    is Resource.Error -> BooksState.error(resource.exception.message.orEmpty())
                    Resource.Loading -> BooksState.loading()
                }
            }
        }
    }

    override fun reorderList(sort: Sort, sortDirection: SortDirection) {
        viewModelScope.launch {
            if (allBooks.isNotEmpty()) {
                val sortedBooks = if (sortDirection == SortDirection.Descending) {
                    allBooks.sortedByDescending { item ->
                        foo(item, sort)
                    }
                } else {
                    allBooks.sortedBy { item ->
                        foo(item, sort)
                    }
                }
                booksState.value = BooksState.success(sortedBooks)
            } else {
                fetchBookList(sort, sortDirection)
            }
        }
    }

    private fun foo(item: BookListItemUI, sortBy: Sort): String {
        return when (sortBy) {
            Sort.Author -> item.author
            Sort.Currency -> item.currencySymbol
            Sort.Name -> item.title
            Sort.Price -> item.price.toString()
        }
    }
}
