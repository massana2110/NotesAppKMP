package com.jetbrains.kmpapp.data.repository

import com.jetbrains.kmpapp.database.dao.CategoriesDao
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.database.dao.SubtaskDao
import com.jetbrains.kmpapp.database.entities.CategoryEntity
import com.jetbrains.kmpapp.database.entities.NoteEntity
import com.jetbrains.kmpapp.database.entities.SubtaskEntity

class NoteRepository(
    private val notesDao: NotesDao,
    private val categoriesDao: CategoriesDao,
    private val subtaskDao: SubtaskDao
) {

    // GET Operations
    fun getAllNotesFromDb() = notesDao.getAllNotes()

    fun getAllCategoriesFromDb() = categoriesDao.getAllCategories()

    suspend fun getCategoryForNote(categoryId: Int) = categoriesDao.getCategoryForNote(categoryId)

    fun getSubtasksFlowForNote(noteId: Int) = subtaskDao.getSubtasksForNote(noteId)

    suspend fun getSubtasksListForNote(noteId: Int) = subtaskDao.getSubtasksListForNote(noteId)

    // INSERT Operations
    suspend fun saveCategory(categoryEntity: CategoryEntity): Result<Unit> {
        return try {
            categoriesDao.upsertCategory(categoryEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun insertNoteWithSubtasks(note: NoteEntity, subtasks: List<SubtaskEntity>): Result<Unit> {
        return try {
            val noteId = notesDao.insertNote(note)
            val updatedSubtasks = subtasks.map { it.copy(noteId = noteId.toInt()) }
            if (updatedSubtasks.isNotEmpty()) subtaskDao.insertAll(updatedSubtasks)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}