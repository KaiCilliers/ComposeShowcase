package com.example.composeshowcase.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun BookListItem(
    book: BookListItemUI,
    itemOnClick: (Int) -> Unit = {}
) {
    Surface{
        Card(
            backgroundColor = MaterialTheme.colors.secondary,
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

@Composable
private fun BookListItemContent(book: BookListItemUI) {
    Row {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "by ${book.author}",
                style = MaterialTheme.typography.caption.copy(
                    fontSize = 10.sp
                ),
                color = Color.Gray
            )
            Text(
                text = book.title,
                maxLines = 2,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = "ISBN - ${book.isbn}",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
            )
        }
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = "Book id - ${book.id}",
                style = MaterialTheme.typography.caption.copy(
                    fontSize = 8.sp
                ),
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Text(
                text = "${book.currencyCode} ${book.price}",
                modifier = Modifier.align(Alignment.BottomEnd)
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
            currencyCode = "EUR",
            id = 888,
            isbn = "13114141n1241lkn141",
            price = 333,
            title = "Android Application for Dummies"
        ))
    }
}