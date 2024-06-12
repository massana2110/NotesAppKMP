package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.database.NotesDatabase
import com.jetbrains.kmpapp.database.getNotesDatabase
import org.koin.dsl.module

val iOSModule = module {
    single { getNotesDatabase() }

    // Provides DAOs
    single { get<NotesDatabase>().getNotesDao() }
    single { get<NotesDatabase>().getCategoriesDao() }
    single { get<NotesDatabase>().getSubtasksDao() }
}