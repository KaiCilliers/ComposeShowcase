package com.example.composeshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeshowcase.common.BookListScreen
import com.example.composeshowcase.features.bookdetail.BookDetailScreen
import com.example.composeshowcase.features.bookdetail.BookDetailViewModel
import com.example.composeshowcase.features.bookdetail.BookDetailViewModelContract
import com.example.composeshowcase.features.booklist.BookListItemUI
import com.example.composeshowcase.navigation.Screen
import com.example.composeshowcase.features.booklist.BookListViewModel
import com.example.composeshowcase.features.booklist.BookListViewModelContract
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

class MainActivity : ComponentActivity() {

    private val viewModel: BookListViewModelContract by lazy { ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BookListViewModel::class.java) }
    private val viewModelDetail: BookDetailViewModelContract by lazy { ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BookDetailViewModel::class.java) }

    private val books: MutableList<BookListItemUI> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShowcaseTheme {
                val navController = rememberNavController()
                NavigationComponent(
                    navController = navController,
                    vm = viewModel,
                    vmDetail = viewModelDetail
                )
            }
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    vm: BookListViewModelContract,
    vmDetail: BookDetailViewModelContract
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            BookListScreen(
                viewModel = vm,
                showDetailScreen = {
                    navController.navigate(Screen.Detail.createRoute(it))
                }
            )
        }
        composable(Screen.Detail.route) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getString("bookId")
            bookId?.let {
                println("book what is got $it")
                vmDetail.fetchBookDetail(bookId.toInt())
                BookDetailScreen(
                    viewModel = vmDetail,
                    navigateUp = { navController.popBackStack() },
                    bookId = it.toInt()
                )
            }
        }
    }
}