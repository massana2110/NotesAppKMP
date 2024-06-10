package com.jetbrains.kmpapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryName: String,
    val categoryColor: String
)
