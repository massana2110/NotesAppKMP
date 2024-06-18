package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.categories.GetCategoriesUseCase
import com.jetbrains.kmpapp.domain.categories.SaveCategoryUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.DeleteNoteWithSubtasksUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.GetAllNotesUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.UpdateNoteUseCase
import com.jetbrains.kmpapp.domain.notes.usecases.UpsertNoteUseCase
import com.jetbrains.kmpapp.presentation.viewmodels.notes.AddNoteViewModel
import com.jetbrains.kmpapp.presentation.viewmodels.notes.NotesListViewModel
import org.koin.dsl.module

val commonModule = module {

    // Repositories
    single { NoteRepository(notesDao = get(), categoriesDao = get(), subtaskDao = get()) }

    // Use cases
    factory { GetAllNotesUseCase(get()) }
    factory { UpsertNoteUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { SaveCategoryUseCase(get()) }
    factory { UpdateNoteUseCase(get()) }
    factory { DeleteNoteWithSubtasksUseCase(get()) }

    // ScreenModels
    factory { NotesListViewModel(get(), get(), get()) }
    factory { AddNoteViewModel(get(), get(), get()) }
}