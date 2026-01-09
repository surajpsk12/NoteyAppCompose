package com.surajvanshsv.noteyappcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajvanshsv.noteyappcompose.repository.NotesRepository
import com.surajvanshsv.noteyappcompose.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private var repository: NotesRepository) : ViewModel() {

    var allNotes : LiveData<List<Note>> = repository.allNotes

    fun insertNote(note : Note) = viewModelScope.launch {
        repository.insertNote(note)
    }



}