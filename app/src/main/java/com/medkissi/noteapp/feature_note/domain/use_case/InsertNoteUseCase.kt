package com.medkissi.noteapp.feature_note.domain.use_case

import com.medkissi.noteapp.feature_note.data.model.InvalidNoteException
import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class InsertNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator  fun invoke(note: Note){
        if (note.title.isBlank() ){
            throw  InvalidNoteException("The title should not be empty...")
        }
        if (note.content.isBlank()){
            throw  InvalidNoteException("The content should not be empty...")

        }
        repository.insertNote(note)
    }
}