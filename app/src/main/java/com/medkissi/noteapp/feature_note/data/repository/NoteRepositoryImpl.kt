package com.medkissi.noteapp.feature_note.data.repository

import com.medkissi.noteapp.feature_note.data.datasource.NoteDao
import com.medkissi.noteapp.feature_note.data.mappper.toNote
import com.medkissi.noteapp.feature_note.data.mappper.toNoteDb
import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl (
    private val noteDao:NoteDao
):NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { notes ->
            notes.map {
                it.toNote()
            }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.toNote()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toNoteDb())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteDb())
    }
}