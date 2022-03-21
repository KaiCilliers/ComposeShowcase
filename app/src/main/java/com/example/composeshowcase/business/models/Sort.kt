package com.example.composeshowcase.business.models

sealed interface Sort {
    object Price : Sort
    object Name : Sort
    object Author : Sort
    object Currency : Sort
}

sealed interface SortDirection {
    object Ascending : SortDirection
    object Descending : SortDirection
}