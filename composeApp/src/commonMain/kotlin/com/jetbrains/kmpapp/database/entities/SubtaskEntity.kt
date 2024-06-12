package com.jetbrains.kmpapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "subtasks_table",
    foreignKeys = [ForeignKey(
        entity = NoteEntity::class,
        parentColumns = ["id"],
        childColumns = ["noteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubtaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteId: Int,
    val subtaskName: String,
    val isCompleted: Boolean,
)
