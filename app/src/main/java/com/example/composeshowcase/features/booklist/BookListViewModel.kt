package com.example.composeshowcase.features.booklist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.composeshowcase.business.FetchBookListUseCase
import com.example.composeshowcase.business.models.Resource
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

    init {
        fetchBookList()
    }

    override fun fetchBookList() {
        viewModelScope.launch {
            booksUseCase().collect { resource ->
                booksState.value = when(resource) {
                    is Resource.Success -> BooksState.success(resource.data.map { it.toUidModel() })
                    is Resource.Error -> BooksState.error(resource.exception.message.orEmpty())
                    Resource.Loading -> BooksState.loading()
                }
            }
        }
    }

}
