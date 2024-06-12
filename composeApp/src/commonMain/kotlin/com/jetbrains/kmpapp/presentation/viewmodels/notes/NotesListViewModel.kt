package com.jetbrains.kmpapp.presentation.viewmodels.notes

import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
): ScreenModel {

}