package com.example.composeshowcase.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO can be more generic to replace with other 3rd party lib
object RemoteDataSource {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://tpbookserver.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookService: BookServiceContract by lazy { retrofit.create(BookService::class.java) }
}