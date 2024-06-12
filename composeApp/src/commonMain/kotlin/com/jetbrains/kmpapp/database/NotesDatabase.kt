package com.jetbrains.kmpapp.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetbrains.kmpapp.database.dao.CategoriesDao
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.database.dao.SubtaskDao
import com.jetbrains.kmpapp.database.entities.CategoryEntity
import com.jetbrains.kmpapp.database.entities.NoteEntity
import com.jetbrains.kmpapp.database.entities.SubtaskEntity

@Database(
    entities = [NoteEntity::class, CategoryEntity::class, SubtaskEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    abstract fun getCategoriesDao(): CategoriesDao

    abstract fun getSubtasksDao(): SubtaskDao
}