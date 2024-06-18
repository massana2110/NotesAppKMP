package com.jetbrains.kmpapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetbrains.kmpapp.database.entities.SubtaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subtasks: List<SubtaskEntity>)

    @Query("SELECT * FROM subtasks_table WHERE noteId = :noteId")
    fun getSubtasksForNote(noteId: Int): Flow<List<SubtaskEntity>>

    @Query("SELECT * FROM subtasks_table WHERE noteId = :noteId")
    suspend fun getSubtasksListForNote(noteId: Int): List<SubtaskEntity>

    @Query("DELETE FROM subtasks_table WHERE noteId = :noteId")
    suspend fun deleteSubtasksForNote(noteId: Int)
}