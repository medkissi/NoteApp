package com.medkissi.noteapp.feature_note.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.noteapp.feature_note.data.model.InvalidNoteException
import com.medkissi.noteapp.feature_note.data.model.Notedb
import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.use_case.NoteUseCases
import com.medkissi.noteapp.feature_note.presentation.state.AddEditNoteEvent
import com.medkissi.noteapp.feature_note.presentation.state.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUsesCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var noteTitle by mutableStateOf(NoteTextFieldState(hint = "Enter title..."))
        private set
    var noteContent by mutableStateOf(NoteTextFieldState(hint = "Enter content... "))
        private set
    var noteColor by mutableStateOf(Note.noteColors.random().toArgb())
        private set
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    notesUsesCases.getNoteByIdUseCase(noteId)?.also {
                        currentNoteId = it.id
                        noteTitle = noteTitle.copy(text = it.title, isHintVisible = false)
                        noteContent = noteContent.copy(text = it.content, isHintVisible = false)
                        noteColor = it.color

                    }


                }
            }
        }
    }


    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                noteTitle = noteTitle.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                noteTitle = noteTitle.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.text.isBlank()
                )

            }

            is AddEditNoteEvent.EnteredContent -> {
                noteContent = noteContent.copy(
                    text = event.value
                )
            }


            is AddEditNoteEvent.ChangeContentFocus -> {
                noteContent = noteContent.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeColor -> {
                noteColor = event.color

            }


            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUsesCases.insertNoteUseCase(
                            Note(
                                title = noteTitle.text,
                                content = noteContent.text,
                                color = noteColor,
                                timestamp = System.currentTimeMillis(),
                                id = currentNoteId
                            )

                        )
                        _eventFlow.emit(UiEvent.SaveNote)

                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )

                    }
                }
            }
        }
    }


}

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
}