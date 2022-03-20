package com.example.composeshowcase.features.bookdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeshowcase.business.FetchBookDetailUseCase
import com.example.composeshowcase.extensions.toUiModel
import com.example.composeshowcase.models.Resource
import com.example.composeshowcase.network.Network
import com.example.composeshowcase.network.RemoteDateSource
import com.example.composeshowcase.repository.BookRepository
import kotlinx.coroutines.launch

class BookDetailViewModel : ViewModel(), BookDetailViewModelContract {

    override val bookState: MutableState<BookDetailState> = mutableStateOf(BookDetailState.loading())

    // todo inject via constructor
    private val bookDetailUseCase by lazy {
        FetchBookDetailUseCase(
            BookRepository(
                RemoteDateSource(Network.bookService)
            )
        )
    }

    override fun fetchBookDetail(id: Int) {
        viewModelScope.launch {
            bookDetailUseCase.bookDetail(id).collect { resource ->
                bookState.value = when (resource) {
                    is Resource.Error -> BookDetailState.error(resource.exception.message.orEmpty())
                    Resource.Loading -> BookDetailState.loading()
                    is Resource.Success -> BookDetailState.success(resource.data.toUiModel())
                }
            }
        }
    }
}