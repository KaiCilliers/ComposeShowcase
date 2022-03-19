package com.example.composeshowcase.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.composeshowcase.business.FetchBookListUseCase
import com.example.composeshowcase.business.Resource
import com.example.composeshowcase.extensions.toUidModel
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.network.RemoteDataSource
import com.example.composeshowcase.network.RemoteDateSource
import com.example.composeshowcase.repository.BookRepository
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {

    private val booksUseCase by lazy {
        FetchBookListUseCase(
            BookRepository(
                RemoteDateSource(RemoteDataSource.bookService)
            )
        )
    }

    // todo replace with interface to remove backing field
    private val _state: MutableState<BooksState> = mutableStateOf(BooksState.loading())
    val state: State<BooksState> = _state

    init {
        fetchBookList()
    }

    private fun fetchBookList() = viewModelScope.launch {
        booksUseCase().collect { resource ->
            _state.value = when(resource) {
                is Resource.Success -> BooksState.success(resource.data.map { it.toUidModel() })
                is Resource.Error -> BooksState.error(resource.exception.message ?: resource.exception.message.orEmpty())
                Resource.Loading -> BooksState.loading()
            }
        }
    }

}

sealed class BooksState {
    object Loading : BooksState()
    data class Success(val data: List<BookListItemUI>) : BooksState()
    data class Error(val exception: Exception) : BooksState()

    companion object {
        fun loading() = Loading
        fun success(data: List<BookListItemUI>) = Success(data)
        fun error(errorMessage: String) = Error(Exception(errorMessage))
    }
}