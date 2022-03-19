package com.example.composeshowcase.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
fun BookList(books: List<BookListItemUI>) {
   Scaffold {
       LazyColumn {
           items(books) {
               BookListItem(it)
           }
       }
   }
}

@Composable
fun BookListItem(book: BookListItemUI) {
    // https://images-na.ssl-images-amazon.com/images/I/51UDJ6EbhXL._SX323_BO1,204,203,200_.jpg
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(80.dp),
        elevation = 4.dp
    ) {
        BookListItemContent(book)
    }
}

@Composable
fun BookListItemContent(book: BookListItemUI) {
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
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MoveListPreview() {
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
           BookList(list)
       }
    }
}