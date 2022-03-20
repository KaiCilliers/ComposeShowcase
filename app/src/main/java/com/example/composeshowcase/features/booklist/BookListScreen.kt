package com.example.composeshowcase.features.booklist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeshowcase.common.ErrorContent
import com.example.composeshowcase.common.LoadingContent
import com.example.composeshowcase.common.MyAppBar
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

/**
 * Represent the home screen view stateful
 */
@Composable
fun HomeScreen(
    viewModel: BookListViewModelContract,
    showDetailScreen: (Int) -> Unit = {}
) {
    val state by viewModel.booksState
    HomeScreen(
        booksState = state,
        itemOnClick = { showDetailScreen(it) }
    )
}

/**
 * Represent the home screen view stateless
 */
@Composable
private fun HomeScreen(
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
            HomeScreen(BooksState.success(list))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListErrorPreview() {
    ComposeShowcaseTheme {
        HomeScreen(booksState = BooksState.error("We had a problem"))
    }
}