package com.example.composeshowcase.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun ErrorContent(additionalMessage: String? = null) {
    Text(
        text = "Something went wrong...${additionalMessage.orEmpty()}",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1.copy(
            fontSize = 20.sp
        )
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun ErrorContentPreview() {
    ComposeShowcaseTheme {
        ErrorContent()
    }
}