package com.example.composeshowcase.business.models

data class Book(
    val author: String,
    val currencyCode: String,
    val description: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String
)