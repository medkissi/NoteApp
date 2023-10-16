package com.medkissi.noteapp.feature_note.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medkissi.noteapp.feature_note.data.model.Notedb
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes():Flow<List<Notedb>>

    @Query("SELECT * FROM notes WHERE id =:id")
    suspend fun getNoteById(id:Int):Notedb?

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun  insertNote(note:Notedb)
    @Delete
    suspend fun  deleteNote(note:Notedb)

}