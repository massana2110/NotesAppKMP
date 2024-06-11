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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.database.entities.NoteEntity
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import com.jetbrains.kmpapp.presentation.DeepSkyBlue
import com.jetbrains.kmpapp.presentation.DimGray
import com.jetbrains.kmpapp.presentation.GoldenYellow
import com.jetbrains.kmpapp.presentation.IndianRed
import com.jetbrains.kmpapp.presentation.MediumSeaGreen
import com.jetbrains.kmpapp.presentation.MediumTurquoise
import com.jetbrains.kmpapp.presentation.Purple

data class AddNoteScreen(
    /*private val notesDao: NotesDao, */
    private val onActionsChange: (@Composable RowScope.() -> Unit) -> Unit
) : Screen {

    private val listColors = listOf(
        Pair(GoldenYellow, Color.Black),
        Pair(IndianRed, Color.White),
        Pair(DeepSkyBlue, Color.Black),
        Pair(Purple, Color.White),
        Pair(MediumSeaGreen, Color.Black),
        Pair(DimGray, Color.Black),
        Pair(MediumTurquoise, Color.White)
    )

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var colorSelected by remember { mutableStateOf(listColors.first()) }
        val subtasks = remember { mutableStateListOf<SubtaskModel>() }
        var currentSubtask by remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            onActionsChange {
                IconButton(onClick = {
                    /* TODO: On save note using use cases */
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
            Text("Subtareas", fontWeight = FontWeight.Bold)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(subtasks, key = { it.id }) {
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
                        value = currentSubtask,
                        onValueChange = { currentSubtask = it },
                        label = { Text(text = "Subtask") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                }
                IconButton(onClick = {
                    subtasks.add(
                        SubtaskModel(
                            id = subtasks.size + 1,
                            subtaskName = currentSubtask,
                            isCompleted = false
                        )
                    )
                    currentSubtask = ""
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