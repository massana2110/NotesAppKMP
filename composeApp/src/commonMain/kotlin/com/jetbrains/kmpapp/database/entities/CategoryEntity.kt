package com.jetbrains.kmpapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryName: String,
    @ColumnInfo(defaultValue = "0") val categoryColor: Long
)
