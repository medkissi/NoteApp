package com.medkissi.noteapp.feature_note.presentation.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.medkissi.noteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.medkissi.noteapp.feature_note.presentation.notes.NoteScreen

@Composable
fun NavApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NoteScreen.route ){

        composable(Screen.NoteScreen.route){
            NoteScreen(navController = navController)
        }
        composable(Screen.AddEditNoteScreen.route +
            "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(name = "noteId"){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(name = "noteColor"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )

        ){
            AddEditNoteScreen(navController = navController,
                noteColor = it.arguments?.getInt("noteColor") ?: -1
            )
        }
    }

}