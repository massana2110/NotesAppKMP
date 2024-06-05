package com.jetbrains.kmpapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.jetbrains.kmpapp.database.getNotesDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getNotesDatabase().getNotesDao()
    }
    App(dao)
}
