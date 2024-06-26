package com.jetbrains.kmpapp.presentation.screens.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.jetbrains.kmpapp.domain.notes.models.CategoryModel
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import com.jetbrains.kmpapp.presentation.GoldenYellow
import com.jetbrains.kmpapp.presentation.components.notes.AddCategoryModal
import com.jetbrains.kmpapp.presentation.components.notes.ColorsRowList
import com.jetbrains.kmpapp.presentation.viewmodels.notes.AddNoteViewModel

data class AddNoteScreen(
    private val onActionsChange: (@Composable RowScope.() -> Unit) -> Unit
) : Screen {

    @OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val addNoteViewModel = getScreenModel<AddNoteViewModel>()
        val uiState by addNoteViewModel.uiState.collectAsState()
        var showCategoryDialog by remember { mutableStateOf(false) }
        val localNavigator = LocalNavigator.current

        LaunchedEffect(uiState.noteIsFavorite) {
            onActionsChange {
                IconButton(onClick = {
                    addNoteViewModel.toggleNoteAsFavorite()
                }) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite note",
                        tint = if (uiState.noteIsFavorite) GoldenYellow else LocalContentColor.current
                    )
                }
                IconButton(onClick = {
                    addNoteViewModel.saveNote()
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
                }
            }
        }

        AnimatedVisibility(showCategoryDialog) {
            AddCategoryModal(
                onDismiss = { showCategoryDialog = false },
                onSaveCategory = { categoryName, color ->
                    addNoteViewModel.saveCategory(
                        CategoryModel(0, categoryName, color)
                    )
                }
            )
        }

        AnimatedVisibility(uiState.noteIsSaved) {
            Dialog(onDismissRequest = {}) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Information",
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 24.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Note saved successfully",
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                        Button(onClick = { localNavigator?.pop() }) {
                            Text("Go back")
                        }
                    }
                }
            }
        }

        LaunchedEffect(uiState.categoryIsSaved) {
            if (uiState.categoryIsSaved) {
                showCategoryDialog = false
                addNoteViewModel.resetCategorySaved()
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
                    label = { Text("Title") },
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
                    label = { Text("Content") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Subtasks", fontWeight = FontWeight.Bold)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.subtasks, key = { it.id }) {
                    SubtaskItem(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        it,
                        onCompletedChange = { id, isCompleted ->
                            addNoteViewModel.markSubtaskCompleted(id, isCompleted)
                        },
                        onRemove = { id ->
                            addNoteViewModel.removeSubtask(id)
                        }
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
            Text(text = "Category", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { showCategoryDialog = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add category")
                }
                if (uiState.categories.isNotEmpty()) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        repeat(uiState.categories.size) {
                            Box(
                                modifier = Modifier.border(
                                    1.dp,
                                    uiState.categories[it].categoryColor,
                                    RoundedCornerShape(16.dp)
                                )
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        if (uiState.categoryIdSelected == uiState.categories[it].id)
                                            uiState.categories[it].categoryColor
                                        else Color.Transparent
                                    )
                                    .clickable { addNoteViewModel.onCategoryIdSelected(uiState.categories[it].id) }
                            ) {
                                Text(
                                    uiState.categories[it].categoryName,
                                    modifier = Modifier.padding(horizontal = 16.dp, 4.dp)
                                )
                            }
                        }
                    }
                } else {
                    Text("No categories added")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Select a color", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            ColorsRowList(
                colorSelected = uiState.colorSelected,
                onColorSelected = { addNoteViewModel.onColorSelectedChange(it) }
            )
        }
    }

    @Composable
    fun SubtaskItem(
        modifier: Modifier,
        subtask: SubtaskModel,
        onCompletedChange: (Int, Boolean) -> Unit,
        onRemove: (Int) -> Unit
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Checkbox(
                    checked = subtask.isCompleted,
                    onCheckedChange = { onCompletedChange(subtask.id, it) }
                )
                Text(
                    text = subtask.subtaskName,
                    textDecoration = if (subtask.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            }

            IconButton(onClick = { onRemove(subtask.id) }) {
                Icon(imageVector = Icons.Outlined.Clear, contentDescription = "Remove subtask")
            }
        }
    }
}