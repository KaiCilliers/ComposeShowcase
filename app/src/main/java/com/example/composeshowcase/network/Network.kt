package com.example.composeshowcase.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://tpbookserver.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookService: BookServiceContract by lazy { retrofit.create(BookService::class.java) }
}