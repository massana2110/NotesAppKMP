package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.UpsertNoteUseCase
import com.jetbrains.kmpapp.presentation.viewmodels.notes.AddNoteViewModel
import com.jetbrains.kmpapp.presentation.viewmodels.notes.NotesListViewModel
import org.koin.dsl.module

val commonModule = module {

    // Repositories
    single { NoteRepository(notesDao = get(), categoriesDao = get()) }

    // Use cases
    factory { GetAllNotesUseCase(get()) }
    factory { UpsertNoteUseCase(get()) }

    // ScreenModels
    factory { NotesListViewModel(get()) }
    factory { AddNoteViewModel(get()) }
}