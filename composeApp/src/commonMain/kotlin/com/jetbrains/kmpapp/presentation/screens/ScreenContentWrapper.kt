package com.jetbrains.kmpapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen

@Composable
fun ScreenContentWrapper(
    paddingValues: PaddingValues
) {
    Box(modifier = Modifier.padding(paddingValues)) {
        CurrentScreen()
    }
}