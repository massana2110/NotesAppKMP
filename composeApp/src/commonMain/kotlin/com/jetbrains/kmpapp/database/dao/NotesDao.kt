package com.jetbrains.kmpapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetbrains.kmpapp.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity
}