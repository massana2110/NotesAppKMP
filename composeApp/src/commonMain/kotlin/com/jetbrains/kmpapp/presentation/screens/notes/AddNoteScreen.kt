package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.jetbrains.kmpapp.database.entities.NoteEntity
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import com.jetbrains.kmpapp.presentation.viewmodels.notes.AddNoteViewModel
import com.jetbrains.kmpapp.presentation.viewmodels.notes.listColors

data class AddNoteScreen(
    private val onActionsChange: (@Composable RowScope.() -> Unit) -> Unit
) : Screen {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val addNoteViewModel = getScreenModel<AddNoteViewModel>()
        val uiState by addNoteViewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            onActionsChange {
                IconButton(onClick = {
                    addNoteViewModel.saveNote()
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(uiState.colorSelected.first)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.title,
                    onValueChange = { addNoteViewModel.onNoteTitleChange(it) },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(color = uiState.colorSelected.second),
                    label = { Text("Titulo") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.description,
                    onValueChange = { addNoteViewModel.onNoteDescriptionChange(it) },
                    maxLines = 4,
                    textStyle = TextStyle(color = uiState.colorSelected.second),
                    label = { Text("Descripción") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Subtareas", fontWeight = FontWeight.Bold)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.subtasks, key = { it.id }) {
                    SubtaskItem(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        it
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(Modifier.weight(1f))
                    TextField(
                        value = uiState.currentSubtask,
                        onValueChange = { addNoteViewModel.onNoteSubtaskChange(it) },
                        label = { Text(text = "Subtask") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                }
                IconButton(onClick = {
                    addNoteViewModel.onSubtaskAdded()
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add subtask")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Categoria", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add category")
                }
                FlowRow {
                    repeat(6) {
                        Box(modifier = Modifier.clickable { }) {
                            Text(
                                "Category $it",
                                modifier = Modifier.padding(horizontal = 8.dp, 4.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Selecciona un color", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listColors) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(it.first)
                            .clickable { addNoteViewModel.onColorSelectedChange(it) },
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.colorSelected == it) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = it.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SubtaskItem(
        modifier: Modifier,
        subtask: SubtaskModel
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Checkbox(checked = subtask.isCompleted, onCheckedChange = {})
                Text(text = subtask.subtaskName)
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Clear, contentDescription = "Remove subtask")
            }
        }
    }

    private suspend fun onSaveNote(noteEntity: NoteEntity) {

    }
}