package com.medkissi.noteapp.feature_note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medkissi.noteapp.ui.theme.BabyBlue
import com.medkissi.noteapp.ui.theme.LightGreen
import com.medkissi.noteapp.ui.theme.RedOrange
import com.medkissi.noteapp.ui.theme.RedPink

@Entity(tableName = "notes")
data class Notedb(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {


}
class InvalidNoteException(message:String):Exception(message)