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
import com.example.composeshowcase.models.NetworkResult
import com.example.composeshowcase.network.RemoteDataSource
import com.example.composeshowcase.network.RemoteDateSource
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val fooRemoteDataSource = RemoteDateSource(
        api = RemoteDataSource.bookService
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            fooRemoteDataSource.bookList().also {
                println(it)
                when(it) {
                    is NetworkResult.Error -> println("fek")
                    is NetworkResult.Success -> {
                        fooRemoteDataSource.bookDetail(it.data[0].id).also {
                            println(it)
                        }
                    }
                }
            }
        }
        setContent {
            ComposeShowcaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
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