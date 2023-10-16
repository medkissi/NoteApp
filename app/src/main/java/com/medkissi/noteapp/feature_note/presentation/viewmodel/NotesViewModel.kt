package com.medkissi.noteapp.feature_note.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.use_case.NoteUseCases
import com.medkissi.noteapp.feature_note.domain.util.NoteOrder
import com.medkissi.noteapp.feature_note.domain.util.OrderType
import com.medkissi.noteapp.feature_note.presentation.state.NotesEvent
import com.medkissi.noteapp.feature_note.presentation.state.NotesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases
) : ViewModel() {
    var state by mutableStateOf(NotesState())
        private set
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job?= null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if(state.noteOrder::class == event.noteOrder::class &&
                   state.noteOrder.orderType == event.noteOrder.orderType ){
                    return
                }

            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }

            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote?.let { note ->
                        notesUseCases.insertNoteUseCase(note)
                        recentlyDeletedNote= null

                    }
                }

            }

            is NotesEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )

            }



        }

    }
    private  fun  getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = notesUseCases.getNotesUseCase(noteOrder).onEach { notes->
            state = state.copy(
                notes = notes,
                noteOrder = noteOrder
            )

        }.launchIn(viewModelScope)

    }




}