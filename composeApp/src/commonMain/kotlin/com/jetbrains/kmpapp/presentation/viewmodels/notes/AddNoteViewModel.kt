package com.jetbrains.kmpapp.presentation.viewmodels.notes

import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import com.jetbrains.kmpapp.domain.notes.usecases.UpsertNoteUseCase
import com.jetbrains.kmpapp.presentation.DeepSkyBlue
import com.jetbrains.kmpapp.presentation.DimGray
import com.jetbrains.kmpapp.presentation.GoldenYellow
import com.jetbrains.kmpapp.presentation.IndianRed
import com.jetbrains.kmpapp.presentation.LightPink
import com.jetbrains.kmpapp.presentation.LightYellow
import com.jetbrains.kmpapp.presentation.MediumSeaGreen
import com.jetbrains.kmpapp.presentation.MediumTurquoise
import com.jetbrains.kmpapp.presentation.Purple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

val listColors = listOf(
    Pair(GoldenYellow, Color.Black),
    Pair(IndianRed, Color.White),
    Pair(DeepSkyBlue, Color.Black),
    Pair(Purple, Color.White),
    Pair(MediumSeaGreen, Color.Black),
    Pair(DimGray, Color.Black),
    Pair(MediumTurquoise, Color.White),
    Pair(LightPink, Color.Black),
    Pair(LightYellow, Color.Black),
)

data class AddNoteUiState(
    val title: String = "",
    val description: String = "",
    val colorSelected: Pair<Color, Color> = listColors.first(),
    val subtasks: List<SubtaskModel> = emptyList(),
    val currentSubtask: String = "",
)

class AddNoteViewModel(
    private val upsertNoteUseCase: UpsertNoteUseCase
): ScreenModel {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState: StateFlow<AddNoteUiState> = _uiState

    fun onNoteTitleChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onNoteDescriptionChange(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onNoteSubtaskChange(subtask: String) {
        _uiState.update { it.copy(currentSubtask = subtask) }
    }

    fun onSubtaskAdded() {
        val currentSubtask = _uiState.value.currentSubtask
        if (currentSubtask.isNotEmpty()) {
            val newSubtasks = _uiState.value.subtasks + SubtaskModel(
                id = _uiState.value.subtasks.size + 1,
                subtaskName = currentSubtask,
                isCompleted = false
            )
            _uiState.update {
                it.copy(subtasks = newSubtasks, currentSubtask = "")
            }
        }
    }

    fun onColorSelectedChange(colorSelected: Pair<Color, Color>) {
        _uiState.update { it.copy(colorSelected = colorSelected) }
    }

    fun saveNote() {}

}