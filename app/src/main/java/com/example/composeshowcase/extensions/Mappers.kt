package com.example.composeshowcase.extensions

import com.example.composeshowcase.business.models.Book
import com.example.composeshowcase.business.models.BookListItem
import com.example.composeshowcase.features.bookdetail.BookUI
import com.example.composeshowcase.features.booklist.BookListItemUI
import com.example.composeshowcase.network.models.BookDetailResponse
import com.example.composeshowcase.network.models.BookListItemResponse

fun BookListItemResponse.toBusinessModel() = BookListItem(
    author = author,
    currencyCode = currencyCode,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

fun BookDetailResponse.toBusinessModel() = Book(
    author = author,
    currencyCode = currencyCode,
    description = description,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

fun BookListItem.toUidModel() = BookListItemUI(
    author = author,
    currencyCode = currencyCode,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)

fun Book.toUiModel() = BookUI(
    author = author,
    currencyCode = currencyCode,
    description = description,
    id = id,
    isbn = isbn,
    price = price,
    title = title
)