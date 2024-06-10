package com.jetbrains.kmpapp.data.mapper

import androidx.compose.ui.graphics.Color
import com.jetbrains.kmpapp.database.entities.CategoryEntity
import com.jetbrains.kmpapp.database.entities.NoteEntity
import com.jetbrains.kmpapp.database.entities.SubtaskEntity
import com.jetbrains.kmpapp.domain.notes.models.CategoryModel
import com.jetbrains.kmpapp.domain.notes.models.NoteModel
import com.jetbrains.kmpapp.domain.notes.models.SubtaskModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun NoteModel.toNoteEntity() = NoteEntity(
    title = title,
    content = content,
    category = category.id,
    color = color.value.toLong(),
    isFavorite = isFavorite,
    createdAt = createdAt.toInstant(TimeZone.currentSystemDefault()).toString()
)

fun NoteEntity.toDomain(categoryEntity: CategoryEntity, subtasks: List<SubtaskEntity>) = NoteModel(
    noteId = id,
    category = categoryEntity.toDomain(),
    title = title,
    content = content,
    color = Color(color.toULong()),
    isFavorite = isFavorite,
    createdAt = Instant.parse(createdAt).toLocalDateTime(TimeZone.currentSystemDefault()),
    subtasks = subtasks.map { it.toDomain() }
)

fun SubtaskEntity.toDomain() = SubtaskModel(
    id = id,
    subtaskName = subtaskName,
    isCompleted = isCompleted
)

fun CategoryEntity.toDomain() = CategoryModel(
    id = id
)