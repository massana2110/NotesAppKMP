package com.jetbrains.kmpapp.data.repository

import com.jetbrains.kmpapp.database.dao.CategoriesDao
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.database.entities.NoteEntity

class NoteRepository(
    private val notesDao: NotesDao,
    private val categoriesDao: CategoriesDao
) {

    fun getAllNotesFromDb() = notesDao.getAllNotes()

    suspend fun saveNote(noteEntity: NoteEntity) {
        notesDao.upsert(noteEntity)
    }
}