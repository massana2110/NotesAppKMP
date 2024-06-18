package com.jetbrains.kmpapp.presentation.viewmodels.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.DpOffset
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

enum class SearchWidgetState {
    OPENED,
    CLOSED
}

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ScreenModel {

    private val _uiState = MutableStateFlow(NotesListUiState())
    val uiState: StateFlow<NotesListUiState> = _uiState

    private val _searchWidgetState = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState = mutableStateOf("")
    val searchText: State<String> = _searchTextState

    private val _isDropdownVisible = mutableStateOf(false)
    val isDropdownVisible: State<Boolean> = _isDropdownVisible

    private val _pressOffset = mutableStateOf(DpOffset.Zero)
    val pressOffset: State<DpOffset> = _pressOffset

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

    fun updateDropdownVisibility(newValue: Boolean) {
        _isDropdownVisible.value = newValue
    }

    fun updateDpOffset(newValue: DpOffset) {
        _pressOffset.value = newValue
    }
}