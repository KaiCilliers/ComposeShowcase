package com.example.composeshowcase.business.models

data class BookListItem(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)