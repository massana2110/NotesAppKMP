package com.jetbrains.kmpapp.domain.notes.usecases

import com.jetbrains.kmpapp.data.mapper.toEntityWithId
import com.jetbrains.kmpapp.data.mapper.toNoteEntityWithId
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.NoteModel

class DeleteNoteWithSubtasksUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(noteModel: NoteModel) = noteRepository.deleteNoteWithSubtasks(
        noteModel.toNoteEntityWithId(),
        noteModel.subtasks.map { it.toEntityWithId() }
    )

}