package com.jetbrains.kmpapp.domain.notes.models

import androidx.compose.ui.graphics.Color

data class CategoryModel(
    val id: Int,
    val categoryName: String,
    val categoryColor: Color
)
