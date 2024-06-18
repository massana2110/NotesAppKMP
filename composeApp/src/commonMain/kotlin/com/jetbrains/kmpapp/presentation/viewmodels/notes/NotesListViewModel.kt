package com.jetbrains.kmpapp.presentation.viewmodels.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.domain.notes.models.NoteModel
import com.jetbrains.kmpapp.domain.notes.usecases.DeleteNoteWithSubtasksUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NotesListUiState(
    val notesList: List<NoteModel> = emptyList(),
    val noteUpdated: Boolean = false
)

enum class SearchWidgetState {
    OPENED,
    CLOSED
}

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteWithSubtasksUseCase: DeleteNoteWithSubtasksUseCase
) : ScreenModel {

    private val _uiState = MutableStateFlow(NotesListUiState())
    val uiState: StateFlow<NotesListUiState> = _uiState

    private val _searchWidgetState = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState = mutableStateOf("")
    val searchText: State<String> = _searchTextState

    init {
        getNotesFromDb()
    }

    private fun getNotesFromDb() {
        getAllNotesUseCase()
            .onEach { notes ->
                _uiState.update { it.copy(notesList = notes) }
            }.launchIn(screenModelScope)
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun onSearchTextChange(newValue: String) {
        _searchTextState.value = newValue
    }

    fun toggleFavoriteNote(note: NoteModel, isFavorite: Boolean) {
        screenModelScope.launch {
            val newNote = note.copy(isFavorite = isFavorite)
            updateNoteUseCase(newNote)
                .onSuccess { _uiState.update { it.copy(noteUpdated = true) } }
                .onFailure { e ->
                    _uiState.update { it.copy(noteUpdated = false) }
                    println("Error on toggle favorite note: ${e.message}")
                }
        }
    }

    fun deleteNote(note: NoteModel) {
        screenModelScope.launch {
            deleteNoteWithSubtasksUseCase(note)
                .onSuccess { println("Deleted note") }
                .onFailure { println("Error in delete note: ${it.message}") }
        }
    }
}