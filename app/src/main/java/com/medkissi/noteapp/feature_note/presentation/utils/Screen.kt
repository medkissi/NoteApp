package com.medkissi.noteapp.feature_note.presentation.utils

sealed class Screen(val route: String) {
    object NoteScreen : Screen(route = "note_screen")
    object AddEditNoteScreen : Screen(route = "add_edit_screen")
}