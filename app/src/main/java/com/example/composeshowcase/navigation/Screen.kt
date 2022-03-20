package com.example.composeshowcase.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("detail/{bookId}") {
        fun createRoute(bookId: Int) = "detail/$bookId"
    }
}