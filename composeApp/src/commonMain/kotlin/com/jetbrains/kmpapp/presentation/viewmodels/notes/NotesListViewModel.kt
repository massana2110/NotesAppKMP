package com.jetbrains.kmpapp.presentation.viewmodels.notes

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
): ScreenModel {

    val myNotesList = mutableStateListOf<String>()

    init {
        myNotesList.addAll(getAllNotesUseCase())
    }

}