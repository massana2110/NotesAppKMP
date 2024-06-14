package com.jetbrains.kmpapp.presentation.viewmodels.notes

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.domain.notes.models.NoteModel
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class NotesListUiState(
    val notesList: List<NoteModel> = emptyList()
)

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ScreenModel {

    private val _uiState = MutableStateFlow(NotesListUiState())
    val uiState: StateFlow<NotesListUiState> = _uiState

    init {
        getNotesFromDb()
    }

    private fun getNotesFromDb() {
        getAllNotesUseCase()
            .onEach { notes ->
                _uiState.update { it.copy(notesList = notes) }
            }.launchIn(screenModelScope)
    }
}