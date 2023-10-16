package com.medkissi.noteapp.di

import android.app.Application
import androidx.room.Room
import com.medkissi.noteapp.feature_note.data.datasource.NoteDao
import com.medkissi.noteapp.feature_note.data.datasource.NoteDatabase
import com.medkissi.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.medkissi.noteapp.feature_note.domain.repository.NoteRepository
import com.medkissi.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.medkissi.noteapp.feature_note.domain.use_case.GetNoteByIdUseCase
import com.medkissi.noteapp.feature_note.domain.use_case.GetNotesUseCase
import com.medkissi.noteapp.feature_note.domain.use_case.InsertNoteUseCase
import com.medkissi.noteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDatabase(context: Application): NoteDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).fallbackToDestructiveMigrationFrom()
            .build()

    }

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase):NoteDao{
        return  noteDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun providesNoteRepository(noteDao: NoteDao):NoteRepository{
        return  NoteRepositoryImpl(noteDao)

    }

    @Singleton
    @Provides
    fun providesNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            getNoteByIdUseCase = GetNoteByIdUseCase(repository),
            insertNoteUseCase = InsertNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }

}