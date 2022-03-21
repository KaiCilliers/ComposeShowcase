package com.example.composeshowcase.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

/**
 * Appbar for the application
 */
@Composable
fun MyAppBar(
    sortClickAction: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Book List",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
            )
        },
        actions = {
            IconButton(onClick = { sortClickAction() }) {
                Icon(Icons.Filled.Sort, contentDescription = "Sort Icon")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MyAppBarPreview() {
    ComposeShowcaseTheme {
        MyAppBar()
    }
}