package com.example.composeshowcase.features.booklist

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection
import com.example.composeshowcase.common.ErrorContent
import com.example.composeshowcase.common.LoadingContent
import com.example.composeshowcase.common.MyAppBar
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

/**
 * Represent the home screen view stateful
 */
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: BookListViewModelContract,
    showDetailScreen: (Int) -> Unit = {},
    retryAction: () -> Unit = {},
    sortAction: (Sort, SortDirection) -> Unit = { _, _ -> }
) {
    val state by viewModel.booksState
    HomeScreen(
        booksState = state,
        itemOnClick = { showDetailScreen(it) },
        retryAction = retryAction,
        sortAction = sortAction
    )
}

/**
 * Represent the home screen view stateless
 */
@ExperimentalFoundationApi
@Composable
private fun HomeScreen(
    booksState: BooksState,
    itemOnClick: (Int) -> Unit = {},
    retryAction: () -> Unit = {},
    sortAction: (Sort, SortDirection) -> Unit = { _, _ -> }
) {

    var showSortOptions by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyAppBar(
                sortClickAction = { showSortOptions = !showSortOptions }
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        when (booksState) {
            is BooksState.Error -> {
                ErrorContent {
                    Button(onClick = { retryAction() }) {
                        Text(text = "Retry")
                    }
                }
            }
            BooksState.Loading -> {
                LoadingContent()
            }
            is BooksState.Success -> {
                Column {
                    AnimatedVisibility(
                        visible = showSortOptions,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        SortSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            onOrderChange = { sortBy, sortDirection ->
                                sortAction(sortBy, sortDirection)
                            }
                        )
                    }
                    LazyColumn {
                        items(
                            items = booksState.data,
                            key = { it.id }
                        ) { item ->
                            BookListItem(
                                modifier = Modifier.animateItemPlacement(),
                                item, itemOnClick = { itemOnClick(it) })
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
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

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun MovieListErrorPreview() {
    ComposeShowcaseTheme {
        HomeScreen(booksState = BooksState.error("We had a problem"))
    }
}