package com.example.composeshowcase.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
fun MyAppBar() {
    TopAppBar{
        Text(
            text = "Book List",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyAppBarPreview() {
    ComposeShowcaseTheme {
        MyAppBar()
    }
}