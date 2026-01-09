package com.surajvanshsv.noteyappcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surajvanshsv.noteyappcompose.repository.NotesRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }

}