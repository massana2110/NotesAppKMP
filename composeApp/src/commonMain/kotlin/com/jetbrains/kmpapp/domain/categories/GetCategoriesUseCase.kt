package com.jetbrains.kmpapp.domain.categories

import com.jetbrains.kmpapp.data.mapper.toDomain
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCategoriesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<CategoryModel>> =
        noteRepository.getAllCategoriesFromDb().map { list ->
            list.map { it.toDomain() }
        }
}