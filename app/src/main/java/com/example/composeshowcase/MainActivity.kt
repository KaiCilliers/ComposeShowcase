package com.example.composeshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.composeshowcase.components.BookListScreen
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.presentation.BookListViewModel
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

class MainActivity : ComponentActivity() {

    private val viewModel: BookListViewModel by lazy { ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BookListViewModel::class.java) }

    private val books: MutableList<BookListItemUI> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShowcaseTheme {
                BookListScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeShowcaseTheme {
        Greeting("Android")
    }
}