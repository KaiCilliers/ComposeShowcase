package com.example.composeshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.composeshowcase.components.BookList
import com.example.composeshowcase.extensions.toUidModel
import com.example.composeshowcase.models.BookListItemUI
import com.example.composeshowcase.models.NetworkResult
import com.example.composeshowcase.network.RemoteDataSource
import com.example.composeshowcase.network.RemoteDateSource
import com.example.composeshowcase.repository.BookRepository
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val fooRepo = BookRepository(
        RemoteDateSource(RemoteDataSource.bookService)
    )

    private val books: MutableList<BookListItemUI> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            fooRepo.allBooks().also {
                println(it)
                it.map { it.toUidModel() }.also {
                    books.addAll(it)
                    setContent {
                        ComposeShowcaseTheme {
                            BookList(books)
                        }
                    }
                }
                if (it.isNotEmpty()) {
                    fooRepo.book(it[0].id).also {
                        println(it)
                    }
                }
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