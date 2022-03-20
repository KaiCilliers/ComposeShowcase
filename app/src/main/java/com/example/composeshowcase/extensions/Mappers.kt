package com.example.composeshowcase.extensions

import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.BookListItem
import com.example.composeshowcase.features.bookdetail.BookUI
import com.example.composeshowcase.features.booklist.BookListItemUI
import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse
import java.util.*

/**
 * Extension to map [BookListItemResponse] to [BookListItem]
 */
fun BookListItemResponse.toBusinessModel() = BookListItem(
    author = author,
    currencyCode = currencyCode,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

/**
 * Extension to map [BookDetailResponse] to [Book]
 */
fun BookDetailResponse.toBusinessModel() = Book(
    author = author,
    currencyCode = currencyCode,
    description = description,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

/**
 * Extension to map [BookListItem] to [BookListItemUI]
 */
fun BookListItem.toUidModel(): BookListItemUI {
    val currencySymbol = Currency.getInstance(currencyCode).getSymbol(Locale.getDefault())
    return BookListItemUI(
        author = author,
        currencySymbol = currencySymbol,
        id = id,
        isbn = isbn,
        price = price,
        title = title
    )
}

/**
 * Extension to map [Book] to [BookUI]
 */
fun Book.toUiModel() = BookUI(
    author = author,
    currencyCode = currencyCode,
    description = description,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

/**
 * Extension to map [BookDetailResponse] to [Book]
 */
fun BookDetailResponse.toBusinessModel2() {
    Book(
        author = author,
        currencyCode = currencyCode,
        description = description,
        id = id,
        isbn = isbn,
        price = price,
        title = title
    )
}