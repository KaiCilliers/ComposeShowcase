package com.example.composeshowcase.extensions

import com.example.composeshowcase.models.*

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