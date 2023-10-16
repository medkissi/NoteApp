package com.medkissi.noteapp.feature_note.presentation.state

data class NoteTextFieldState(
    var text:String ="",
    var hint:String ="",
    var isHintVisible:Boolean = false
)
