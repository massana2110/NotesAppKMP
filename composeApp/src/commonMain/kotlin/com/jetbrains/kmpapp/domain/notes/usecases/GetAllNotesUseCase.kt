package com.jetbrains.kmpapp.domain.notes.usecases

import com.jetbrains.kmpapp.data.mapper.toDomain
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotesUseCase(
    private val noteRepository: NoteRepository
) {

    operator fun invoke(): Flow<List<NoteModel>> {
        return noteRepository.getAllNotesFromDb().map { notes ->
            notes.map { it.toDomain() }
        }
    }

}