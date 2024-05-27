package com.jetbrains.kmpapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetbrains.kmpapp.data.datasource.database.dao.NotesDao
import com.jetbrains.kmpapp.data.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}