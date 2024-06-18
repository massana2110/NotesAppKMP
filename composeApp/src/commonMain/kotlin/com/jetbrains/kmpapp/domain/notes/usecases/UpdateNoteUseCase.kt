package com.jetbrains.kmpapp.domain.notes.usecases

import com.jetbrains.kmpapp.data.mapper.toNoteEntityWithId
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.NoteModel

class UpdateNoteUseCase(
    private val notesRepository: NoteRepository
) {

    suspend operator fun invoke(noteModel: NoteModel) = notesRepository.updateNote(
        noteModel.toNoteEntityWithId()
    )

}