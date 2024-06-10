package com.jetbrains.kmpapp.domain.notes.models

data class SubtaskModel(
    val id: Int,
    val subtaskName: String,
    val isCompleted: Boolean
)
