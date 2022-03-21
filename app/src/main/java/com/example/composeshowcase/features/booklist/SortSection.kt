package com.example.composeshowcase.features.booklist

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeshowcase.business.models.Sort
import com.example.composeshowcase.business.models.SortDirection
import com.example.composeshowcase.ui.theme.ComposeShowcaseTheme

@Composable
fun SortSection(
    modifier: Modifier = Modifier,
    onOrderChange: (Sort, SortDirection) -> Unit
) {
    var currentSort: Sort by remember { mutableStateOf(Sort.Name) }
    var currentDirection: SortDirection by remember{ mutableStateOf(SortDirection.Descending) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = Sort.Name::class.java.simpleName,
                selected = currentSort is Sort.Name,
                onSelect = {
                    currentSort = Sort.Name
                    onOrderChange(currentSort, currentDirection)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = Sort.Price::class.java.simpleName,
                selected = currentSort is Sort.Price,
                onSelect = {
                    currentSort = Sort.Price
                    onOrderChange(currentSort, currentDirection)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = Sort.Author::class.java.simpleName,
                selected = currentSort is Sort.Author,
                onSelect = {
                    currentSort = Sort.Author
                    onOrderChange(currentSort, currentDirection)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = Sort.Currency::class.java.simpleName,
                selected = currentSort is Sort.Currency,
                onSelect = {
                    currentSort = Sort.Currency
                    onOrderChange(currentSort, currentDirection)
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = SortDirection.Ascending::class.java.simpleName,
                selected = currentDirection is SortDirection.Ascending,
                onSelect = {
                    currentDirection = SortDirection.Ascending
                    onOrderChange(currentSort, currentDirection)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = SortDirection.Descending::class.java.simpleName,
                selected = currentDirection is SortDirection.Descending,
                onSelect = {
                    currentDirection = SortDirection.Descending
                    onOrderChange(currentSort, currentDirection)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SortSectionPreview() {
    ComposeShowcaseTheme {
        SortSection(onOrderChange = {_,_ -> })
    }
}