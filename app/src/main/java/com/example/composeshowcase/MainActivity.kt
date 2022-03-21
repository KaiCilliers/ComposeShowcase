package com.example.composeshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeshowcase.features.booklist.HomeScreen
import com.example.composeshowcase.features.bookdetail.BookDetailScreen
import com.example.composeshowcase.features.bookdetail.BookDetailViewModel
import com.example.composeshowcase.navigation.Screen
import com.example.composeshowcase.features.booklist.BookListViewModel
import com.example.composeshowcase.features.booklist.BookListViewModelContract
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val viewModel: BookListViewModelContract by lazy { ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BookListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShowcaseTheme {
                val navController = rememberNavController()
                NavigationComponent(
                    navController = navController,
                    vm = viewModel
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    vm: BookListViewModelContract
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = vm,
                showDetailScreen = {
                    navController.navigate(Screen.Detail.createRoute(it))
                },
                retryAction = { vm.fetchBookList() },
                sortAction =  {sort, sortDirection ->
                    vm.reorderList(sort, sortDirection)
                }
            )
        }
        composable(Screen.Detail.route) { navBackStackEntry ->
            val viewModel: BookDetailViewModel = viewModel()
            val bookId = navBackStackEntry.arguments?.getString("bookId")
            bookId?.let {
                println("book what is got $it")
                viewModel.fetchBookDetail(bookId.toInt())
                BookDetailScreen(
                    navigateUp = { navController.popBackStack() },
                    bookId = it.toInt(),
                    viewModel = viewModel
                )
            }
        }
    }
}