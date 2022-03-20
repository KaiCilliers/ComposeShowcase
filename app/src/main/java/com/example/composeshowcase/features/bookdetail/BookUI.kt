package com.example.composeshowcase.features.bookdetail

/**
 * UI model representing a book
 */
data class BookUI(
    val author: String,
    val currencyCode: String,
    val description: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)