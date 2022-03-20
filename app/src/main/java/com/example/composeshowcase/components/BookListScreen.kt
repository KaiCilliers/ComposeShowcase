package com.example.composeshowcase.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.presentation.BookListViewModel
import com.example.composeshowcase.presentation.BooksState
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun BookListScreen(viewModel: BookListViewModel) {
    val state by viewModel.booksState
    BookListScreen(booksState = state)
}

@Composable
private fun BookListScreen(booksState: BooksState) {
    Scaffold(
        topBar = { MyAppBar() },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        when (booksState) {
            is BooksState.Error -> {
                ErrorContent(booksState.exception.message)
            }
            BooksState.Loading -> { 
                LoadingContent()
            }
            is BooksState.Success -> {
                LazyColumn {
                    val multiList = mutableListOf<BookListItemUI>().apply { addAll(booksState.data); addAll(booksState.data); addAll(booksState.data) }
                    items(multiList) {
                        BookListItem(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoveListContentPreview() {
    ComposeShowcaseTheme {
        val item = BookListItemUI(
            "Patricia Gibney",
            "$",
            100,
            "112948102741209313",
            500,
            "The Missing Ones: The Complete Collection with an extra long title"
        )
        mutableListOf<BookListItemUI>().let { list ->
            repeat(20) {
                list.add(item)
            }
            BookListScreen(BooksState.success(list))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListLoadingPreview() {
    ComposeShowcaseTheme {
        BookListScreen(booksState = BooksState.loading())
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListErrorPreview() {
    ComposeShowcaseTheme {
        BookListScreen(booksState = BooksState.error("We had a problem"))
    }
}