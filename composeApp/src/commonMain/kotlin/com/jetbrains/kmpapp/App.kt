package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.presentation.components.core.NotesTopAppBar
import com.jetbrains.kmpapp.presentation.screens.ScreenContentWrapper
import com.jetbrains.kmpapp.presentation.screens.notes.NotesListScreen

@Composable
fun App(notesDao: NotesDao) {
    MaterialTheme {
        var showBackButton by remember { mutableStateOf(false) }
        var topBarActions by remember { mutableStateOf<@Composable RowScope.() -> Unit>({}) }

        Navigator(
            NotesListScreen(
                notesDao = notesDao,
                onActionsChange = { actions -> topBarActions = actions })
        ) {
            Scaffold(
                topBar = {
                    NotesTopAppBar(
                        currentTitle = "Notes",
                        onBackClick = if (showBackButton) {
                            { it.pop() }
                        } else null,
                        actions = topBarActions
                    )
                },
                content = { pv ->
                    ScreenContentWrapper(pv)
                },
            )

            LaunchedEffect(it.lastItem) {
                showBackButton = it.items.size > 1
            }
        }
    }
}