package com.example.composeshowcase.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.presentation.BookListViewModel
import com.example.composeshowcase.models.BooksState
import com.example.composeshowcase.navigation.Screen
import com.example.composeshowcase.presentation.BookListViewModelContract
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun BookListScreen(
    viewModel: BookListViewModelContract,
    showDetailScreen: (Int) -> Unit = {}
) {
    val state by viewModel.booksState
    BookListScreen(
        booksState = state,
        itemOnClick = { showDetailScreen(it) }
    )
}

@Composable
private fun BookListScreen(
    booksState: BooksState,
    itemOnClick: (Int) -> Unit = {}
) {
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
                    items(multiList) { item ->
                        BookListItem(item, itemOnClick = { itemOnClick(it) })
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
private fun MovieListErrorPreview() {
    ComposeShowcaseTheme {
        BookListScreen(booksState = BooksState.error("We had a problem"))
    }
}