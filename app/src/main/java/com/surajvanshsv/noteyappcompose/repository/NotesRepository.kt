package com.surajvanshsv.noteyappcompose.repository

import androidx.lifecycle.LiveData
import com.surajvanshsv.noteyappcompose.roomdb.Note
import com.surajvanshsv.noteyappcompose.roomdb.NoteDao

class NotesRepository(private  val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note : Note){
        return noteDao.insert(note)
    }

}