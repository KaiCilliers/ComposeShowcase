package com.example.composeshowcase.features.booklist

/**
 * UI model representing a book list item
 */
data class BookListItemUI(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)