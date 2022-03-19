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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.presentation.BookListViewModel
import com.example.composeshowcase.presentation.BooksState
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun BookList(vm: BookListViewModel) {
    val state by vm.state
    BookListScaffold(booksState = state)
}

@Composable
private fun BookListScaffold(booksState: BooksState) {
    Scaffold(
        topBar = {
            // todo move to separate file MyAppBar
            TopAppBar{
                Text(
                    text = "Book List",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        when (booksState) {
            is BooksState.Error -> {
                Box {
                    // todo this box could be included in loading and error components to fill its container
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ErrorContent(booksState.exception.message)
                    }
                }
            }
            BooksState.Loading -> { 
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoadingContent()
                }
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
            BookListScaffold(BooksState.success(list))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListLoadingPreview() {
    ComposeShowcaseTheme {
        BookListScaffold(booksState = BooksState.loading())
    }
}