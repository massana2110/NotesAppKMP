package com.jetbrains.kmpapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.presentation.screens.notes.NotesScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(NotesScreen)
    }
}
