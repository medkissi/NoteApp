package com.medkissi.noteapp.feature_note.presentation.state

import com.medkissi.noteapp.feature_note.domain.model.Note
import com.medkissi.noteapp.feature_note.domain.util.NoteOrder
import com.medkissi.noteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes:List<Note> = emptyList(),
    val noteOrder:NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
