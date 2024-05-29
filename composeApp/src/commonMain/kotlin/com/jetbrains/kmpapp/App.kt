package com.jetbrains.kmpapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.presentation.components.core.NotesTopAppBar
import com.jetbrains.kmpapp.presentation.screens.ScreenContentWrapper
import com.jetbrains.kmpapp.presentation.screens.notes.AddNoteScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(AddNoteScreen) {
            Scaffold(
                topBar = {
                    NotesTopAppBar(
                        currentTitle = "Notes",
                        onSearchClick = {},
                        onFavoritesClick = {})
                },
                content = { ScreenContentWrapper(it) },
            )
        }
    }
}
