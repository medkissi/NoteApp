package com.medkissi.noteapp.feature_note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medkissi.noteapp.feature_note.data.model.Notedb

@Database(entities = [Notedb::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
    abstract  fun noteDao():NoteDao
}