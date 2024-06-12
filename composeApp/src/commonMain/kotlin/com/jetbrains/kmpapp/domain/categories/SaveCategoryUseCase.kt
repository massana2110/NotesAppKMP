package com.jetbrains.kmpapp.domain.categories

import com.jetbrains.kmpapp.data.mapper.toEntity
import com.jetbrains.kmpapp.data.repository.NoteRepository
import com.jetbrains.kmpapp.domain.notes.models.CategoryModel

class SaveCategoryUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(categoryModel: CategoryModel) {
        noteRepository.saveCategory(categoryModel.toEntity())
    }
}