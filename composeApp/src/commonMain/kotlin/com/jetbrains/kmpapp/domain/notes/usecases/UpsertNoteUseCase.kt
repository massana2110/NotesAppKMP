package com.jetbrains.kmpapp.domain.notes.usecases

import com.jetbrains.kmpapp.data.mapper.toEntity
import com.jetbrains.kmpapp.data.mapper.toNoteEntity
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.NoteModel

class UpsertNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(noteModel: NoteModel) = noteRepository.insertNoteWithSubtasks(
        note = noteModel.toNoteEntity(),
        subtasks = noteModel.subtasks.map { it.toEntity() }
    )

}