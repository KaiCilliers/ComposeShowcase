package com.example.composeshowcase.network.models

data class BookDetailResponse(
    val author: String,
    val currencyCode: String,
    val description: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)