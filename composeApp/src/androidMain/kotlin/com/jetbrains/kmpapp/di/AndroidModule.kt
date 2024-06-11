package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.database.NotesDatabase
import com.jetbrains.kmpapp.database.getNotesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single {
        getNotesDatabase(androidContext())
    }

    // Provides the DAOs
    single { get<NotesDatabase>().getNotesDao()}
    single { get<NotesDatabase>().getCategoriesDao()}
}