package com.example.composeshowcase.network.models

data class BookListItemResponse(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)