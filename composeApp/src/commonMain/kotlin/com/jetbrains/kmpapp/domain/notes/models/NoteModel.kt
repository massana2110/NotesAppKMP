package com.jetbrains.kmpapp.domain.notes.models

import androidx.compose.ui.graphics.Color
import com.jetbrains.kmpapp.domain.utils.DateTimeUtil
import kotlinx.datetime.LocalDateTime

data class NoteModel(
    val noteId: Int,
    val category: CategoryModel,
    val title: String,
    val content: String,
    val color: Color,
    val isFavorite: Boolean,
    val createdAt: LocalDateTime = DateTimeUtil.now(),
    val subtasks: List<SubtaskModel>
)