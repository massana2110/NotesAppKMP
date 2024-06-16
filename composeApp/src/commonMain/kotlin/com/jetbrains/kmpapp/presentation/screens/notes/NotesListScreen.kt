package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.jetbrains.kmpapp.domain.notes.models.NoteModel
import com.jetbrains.kmpapp.domain.utils.DateTimeUtil
import com.jetbrains.kmpapp.presentation.MediumSeaGreen
import com.jetbrains.kmpapp.presentation.viewmodels.notes.NotesListViewModel

data class NotesListScreen(
    private val onActionsChange: (@Composable RowScope.() -> Unit) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = getScreenModel<NotesListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            onActionsChange {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navigator?.push(
                            AddNoteScreen(onActionsChange = { actions ->
                                onActionsChange(actions)
                            })
                        )
                    },
                    containerColor = MediumSeaGreen,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note",
                        tint = Color.White
                    )
                }
            }
        ) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(horizontal = 8.dp),
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = it,
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.notesList, key = { item -> item.noteId }) { note ->
                    NoteItem(note = note)
                }
            }
        }
    }

    @Composable
    fun NoteItem(modifier: Modifier = Modifier, note: NoteModel) {
        Card(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = note.color),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 0.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )
        ) {
            val displayedItems =
                if (note.subtasks.size > 3) note.subtasks.take(3) else note.subtasks

            Row(modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
                if (note.isFavorite) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite",
                        tint = Color.Yellow
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "#${note.category.categoryName}",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = note.title,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp),
                text = note.content,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                color = Color.White
            )

            // Show subtasks
            displayedItems.forEach { item ->
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isCompleted,
                        onCheckedChange = {}, enabled = false,
                        colors = CheckboxDefaults.colors(disabledUncheckedColor = Color.White)
                    )
                    Text(
                        text = item.subtaskName,
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
            }

            // Show more subtasks if number is more than 3 elements
            if (note.subtasks.size > 3) {
                Text(
                    text = "+ ${note.subtasks.size - 3} more",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                textAlign = TextAlign.End,
                text = DateTimeUtil.formatNoteDate(note.createdAt),
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }
    }
}