package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.domain.notes.models.NoteSubtasks

data class AddNoteScreen(private val notesDao: NotesDao) : Screen {

    private val listColors = listOf(
        Pair(Color.Yellow, Color.Black),
        Pair(Color.Red, Color.White),
        Pair(Color.Blue, Color.White),
        Pair(Color.Green, Color.Black),
        Pair(Color.Magenta, Color.Black),
        Pair(Color.Cyan, Color.Black),
        Pair(Color.Gray, Color.Black),
        Pair(Color.Black, Color.White)
    )

    @Composable
    override fun Content() {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var colorSelected by remember { mutableStateOf(Pair(Color.Yellow, Color.Black)) }
        val subtasks = remember {
            mutableStateListOf<NoteSubtasks>(
                NoteSubtasks(1, false, "Subtask 1")
            )
        }

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorSelected.first)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { title = it },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(color = colorSelected.second),
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
                    value = description,
                    onValueChange = { description = it },
                    maxLines = 4,
                    textStyle = TextStyle(color = colorSelected.second),
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
            Text("Subtareas")
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(subtasks, key = { it.subtaskId }) {
                    SubtaskItem(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        it
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(value = "", onValueChange = {})
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add subtask")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Selecciona un color")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listColors) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(it.first)
                            .clickable { colorSelected = it },
                        contentAlignment = Alignment.Center
                    ) {
                        if (colorSelected == it) {
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
        subtask: NoteSubtasks
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Checkbox(checked = subtask.subtaskIsDone, onCheckedChange = {})
                Text(text = subtask.subtaskName)
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Clear, contentDescription = "Remove subtask")
            }
        }
    }
}