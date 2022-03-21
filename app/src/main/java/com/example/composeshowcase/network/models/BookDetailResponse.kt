package com.example.composeshowcase.network.models

/**
 * Network response model representing book detail
 */
data class BookDetailResponse(
    val author: String,
    val currencyCode: String,
    val description: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)