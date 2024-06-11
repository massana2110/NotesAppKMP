package com.jetbrains.kmpapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val category: Int,
    @ColumnInfo(defaultValue = "0") val color: Long,
    val isFavorite: Boolean,
    @ColumnInfo(defaultValue = "") val createdAt: String,
)
