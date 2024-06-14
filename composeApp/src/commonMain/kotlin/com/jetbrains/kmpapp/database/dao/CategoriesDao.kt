package com.jetbrains.kmpapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jetbrains.kmpapp.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Upsert
    suspend fun upsertCategory(categoryEntity: CategoryEntity)

    @Delete
    suspend fun removeCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories_table")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories_table WHERE id = :categoryId")
    suspend fun getCategoryForNote(categoryId: Int): CategoryEntity

}