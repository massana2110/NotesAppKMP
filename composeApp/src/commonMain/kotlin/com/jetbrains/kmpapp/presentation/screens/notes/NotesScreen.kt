package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.presentation.components.core.NotesTopAppBar

data object NotesScreen : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {}, containerColor = Color.Green) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            },
            topBar = {
                NotesTopAppBar(
                    currentTitle = "Notes",
                    onSearchClick = {},
                    onFavoritesClick = {})
            }
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(200.dp),
                contentPadding = it,
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(6) {
                    Card(modifier = Modifier.fillMaxWidth().wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = Color.Yellow)) {
                        Text(text = "Ejemplo de nota")
                    }
                }
            }
        }
    }
}
