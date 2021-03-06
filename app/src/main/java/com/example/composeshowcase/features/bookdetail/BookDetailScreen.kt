package com.example.composeshowcase.features.bookdetail

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.common.ErrorContent
import com.example.composeshowcase.common.LoadingContent
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

/**
 * Book detail screen view stateful
 */
@Composable
fun BookDetailScreen(
    navigateUp: () -> Unit = {},
    viewModel: BookDetailViewModel,
    bookId: Int
) {
    val state by viewModel.bookState
    println("bookID is this $bookId")
    BookDetailScreen(state, navigateUp)
}

/**
 * Book detail screen view stateless
 */
@Composable
fun BookDetailScreen(
    state: BookDetailState,
    navigateUp: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            when (state) {
                is BookDetailState.Error -> {
                    ErrorContent {
                        Button(onClick = { navigateUp() }) {
                            Text(text = "Back to safety")
                        }        
                    }
                }
                BookDetailState.Loading -> {
                    LoadingContent()
                }
                is BookDetailState.Success -> {
                    BookDetailContent(state.data)
                }
            }
        }
    )
}

/**
 * Book detail screen main content
 */
@Composable
fun BookDetailContent(data: BookUI) {

    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(4.dp).fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = data.title,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = data.author,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontStyle = FontStyle.Italic
            )
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = "ISBN - ${data.isbn}",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
            )
            Text(
                text = data.description,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp)
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 800
                        )
                    )
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
            Button(
                onClick = {
                          Toast.makeText(context, "Confirmed purchase for ${data.title}", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "${data.currencySymbol} ${data.price} - Purchase")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun BookDetailScreenPreview() {
    ComposeShowcaseTheme {
        BookDetailScreen(
            state = BookDetailState.success(
                BookUI(
                    author = "Mike Riley",
                    currencySymbol = "EUR",
                    description = "Widely considered one of the best practical guides to programming, Steve McConnell???s original CODE COMPLETE has been helping developers write better software for more than a decade. Now this classic book has been fully updated and revised with leading-edge practices???and hundreds of new code samples???illustrating the art and science of software construction. Capturing the body of knowledge available from research, academia, and everyday commercial practice, McConnell synthesizes the most effective techniques and must-know principles into clear, pragmatic guidance. No matter what your experience level, development environment, or project size, this book will inform and stimulate your thinking???and help you build the highest quality code.",
                    id = 100,
                    isbn = "978-0735619678",
                    price = 2954,
                    title = "Code Complete: A Practical Handbook of Software Construction"
                )
            )
        )
    }
}
