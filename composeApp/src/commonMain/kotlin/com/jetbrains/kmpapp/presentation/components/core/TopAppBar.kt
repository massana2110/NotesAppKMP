package com.jetbrains.kmpapp.presentation.components.core

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopAppBar(
    modifier: Modifier = Modifier,
    currentTitle: String,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = currentTitle) },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { onFavoritesClick() }) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Favorites")
            }
        }
    )
}