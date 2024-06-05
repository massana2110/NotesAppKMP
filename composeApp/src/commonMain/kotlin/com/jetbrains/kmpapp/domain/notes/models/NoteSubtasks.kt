package com.jetbrains.kmpapp.domain.notes.models

data class NoteSubtasks(
    val subtaskId: Int,
    val subtaskIsDone: Boolean = false,
    val subtaskName: String,
)
