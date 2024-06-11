package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.jetbrains.kmpapp.presentation.viewmodels.notes.NotesListViewModel

data class NotesListScreen(
    private val onActionsChange: (@Composable RowScope.() -> Unit) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = getScreenModel<NotesListViewModel>()
        // val notes by notesDao.getAllNotes().collectAsState(initial = emptyList())

        LaunchedEffect(Unit) {
            onActionsChange {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Favorites")
                }
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navigator?.push(AddNoteScreen(onActionsChange = { actions ->
                        onActionsChange(actions)
                    }))
                }, containerColor = Color.Green) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            }
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(200.dp),
                contentPadding = it,
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(viewModel.myNotesList.toList()) {
                    Card(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        colors = CardDefaults.cardColors(containerColor = Color.Yellow)
                    ) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}