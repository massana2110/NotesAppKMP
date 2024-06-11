package com.jetbrains.kmpapp.domain.notes.usecases

import com.jetbrains.kmpapp.data.repository.NoteRepository

class GetAllNotesUseCase(
    private val noteRepository: NoteRepository
) {

    /*operator fun invoke(): Flow<List<NoteModel>> {
        return noteRepository.getAllNotesFromDb().map { notes ->
            notes.map { it.toDomain() }
        }
    }*/

    operator fun invoke(): List<String> {
        return listOf("Nota 1", "Nota 2")
    }

}