package com.medkissi.noteapp.feature_note.data.mappper

import com.medkissi.noteapp.feature_note.data.model.Notedb
import com.medkissi.noteapp.feature_note.domain.model.Note


fun Notedb.toNote(): Note {
    return Note(
        title = title,
        content= content,
        timestamp =timestamp,
        color=color,
        id = id ,
    )

}
fun Note.toNoteDb():Notedb{
    return Notedb(
        title = title,
        content= content,
        timestamp =timestamp,
        color=color,
        id = id ,
    )

}