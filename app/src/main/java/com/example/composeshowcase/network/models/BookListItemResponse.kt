package com.example.composeshowcase.network.models

/**
 * Network response model representing book list item
 */
data class BookListItemResponse(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)