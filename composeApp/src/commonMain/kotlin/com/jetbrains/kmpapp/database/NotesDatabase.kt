package com.jetbrains.kmpapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetbrains.kmpapp.database.dao.NotesDao
import com.jetbrains.kmpapp.database.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}