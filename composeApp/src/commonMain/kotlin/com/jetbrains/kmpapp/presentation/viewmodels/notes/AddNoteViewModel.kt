package com.jetbrains.kmpapp.presentation.viewmodels.notes

import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.domain.categories.GetCategoriesUseCase
import com.jetbrains.kmpapp.domain.categories.SaveCategoryUseCase
import com.jetbrains.kmpapp.domain.notes.models.CategoryModel
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import com.jetbrains.kmpapp.domain.notes.usecases.UpsertNoteUseCase
import com.jetbrains.kmpapp.presentation.components.notes.listColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddNoteUiState(
    val title: String = "",
    val description: String = "",
    val colorSelected: Pair<Color, Color> = listColors.first(),
    val categoryIdSelected: Int = -1,
    val subtasks: List<SubtaskModel> = emptyList(),
    val categories: List<CategoryModel> = emptyList(),
    val currentSubtask: String = "",
)

class AddNoteViewModel(
    private val upsertNoteUseCase: UpsertNoteUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveCategoryUseCase: SaveCategoryUseCase
): ScreenModel {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState: StateFlow<AddNoteUiState> = _uiState

    init {
        loadCategories()
    }

    fun onNoteTitleChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onNoteDescriptionChange(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onNoteSubtaskChange(subtask: String) {
        _uiState.update { it.copy(currentSubtask = subtask) }
    }

    fun onCategoryIdSelected(categoryId: Int) {
        _uiState.update { it.copy(categoryIdSelected = categoryId) }
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

    private fun loadCategories() {
        getCategoriesUseCase()
            .onEach { categories ->
                _uiState.update { it.copy(categories = categories) }
            }
            .launchIn(screenModelScope)
    }

    fun saveCategory(category: CategoryModel) {
        screenModelScope.launch { saveCategoryUseCase(category) }
    }

    fun saveNote() {}

}