package com.jetbrains.kmpapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.presentation.components.core.NotesTopAppBar
import com.jetbrains.kmpapp.presentation.screens.ScreenContentWrapper
import com.jetbrains.kmpapp.presentation.screens.notes.NotesListScreen

@Composable
fun App() {
    MaterialTheme {
        var showBackButton by remember { mutableStateOf(false) }

        Navigator(NotesListScreen) {
            Scaffold(
                topBar = {
                    NotesTopAppBar(
                        currentTitle = "Notes",
                        onBackClick = if (showBackButton) {
                            { it.pop() }
                        } else null,
                        onSearchClick = {},
                        onFavoritesClick = {})
                },
                content = { ScreenContentWrapper(it) },
            )

            LaunchedEffect(it.lastItem) {
                showBackButton = it.items.size > 1
            }
        }
    }
}
