package com.medkissi.noteapp.feature_note.domain.use_case

import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository) {
    suspend operator  fun invoke(id:Int): Note?{
        return repository.getNoteById(id)

    }
}