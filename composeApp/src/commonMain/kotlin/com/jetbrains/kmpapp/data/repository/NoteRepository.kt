package com.jetbrains.kmpapp.data.repository

import com.jetbrains.kmpapp.database.dao.CategoriesDao
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.database.entities.CategoryEntity
import com.jetbrains.kmpapp.database.entities.NoteEntity

class NoteRepository(
    private val notesDao: NotesDao,
    private val categoriesDao: CategoriesDao
) {

    // GET Operations
    fun getAllNotesFromDb() = notesDao.getAllNotes()

    fun getAllCategoriesFromDb() = categoriesDao.getAllCategories()

    // INSERT Operations
    suspend fun saveNote(noteEntity: NoteEntity) {
        notesDao.upsert(noteEntity)
    }

    suspend fun saveCategory(categoryEntity: CategoryEntity) {
        categoriesDao.upsertCategory(categoryEntity)
    }
}