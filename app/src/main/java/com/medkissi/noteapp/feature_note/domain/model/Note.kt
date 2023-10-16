package com.medkissi.noteapp.feature_note.domain.model

import com.medkissi.noteapp.ui.theme.BabyBlue
import com.medkissi.noteapp.ui.theme.LightGreen
import com.medkissi.noteapp.ui.theme.RedOrange
import com.medkissi.noteapp.ui.theme.RedPink

data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    val id:Int?= null
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, BabyBlue, RedPink)

    }
}