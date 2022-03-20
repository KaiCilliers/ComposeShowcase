package com.example.composeshowcase.features.booklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

/**
 * Represent a single item in book list view
 */
@Composable
fun BookListItem(
    book: BookListItemUI,
    itemOnClick: (Int) -> Unit = {}
) {
    Surface{
        Card(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .clickable { itemOnClick(book.id) }
                .fillMaxWidth()
                .padding(10.dp)
                .height(80.dp),
            elevation = 10.dp,
            shape = CutCornerShape(topEnd = 20.dp),
        ) {
            BookListItemContent(book)
        }
    }
}

/**
 * Book list item view content
 */
@Composable
private fun BookListItemContent(book: BookListItemUI) {
    Row {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = book.title,
                maxLines = 2,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 10.sp
                    )
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = "${book.currencySymbol} ${book.price}",
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookListItemPreview() {
    ComposeShowcaseTheme {
        BookListItem(book = BookListItemUI(
            author = "Kevin Felker",
            currencySymbol = "€",
            id = 888,
            isbn = "13114141n1241lkn141",
            price = 333,
            title = "Android Application for Dummies"
        )
        )
    }
}