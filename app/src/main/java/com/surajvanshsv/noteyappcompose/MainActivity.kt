package com.surajvanshsv.noteyappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.surajvanshsv.noteyappcompose.repository.NotesRepository
import com.surajvanshsv.noteyappcompose.roomdb.NoteDao
import com.surajvanshsv.noteyappcompose.roomdb.NotesDb
import com.surajvanshsv.noteyappcompose.screens.DisplayNotesList
import com.surajvanshsv.noteyappcompose.ui.theme.NoteyAppComposeTheme
import com.surajvanshsv.noteyappcompose.viewmodel.NoteViewModel
import com.surajvanshsv.noteyappcompose.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // room db
        val database = NotesDb.getInstance(context = applicationContext)

        // repository
        val repository = NotesRepository(database!!.notesdao)

        // viewmodel factory
        val viewModelFactory = NoteViewModelFactory(repository)

        // viwemodel
        val noteViewModel = ViewModelProvider(this,
            viewModelFactory)[NoteViewModel::class.java]


        setContent {
            NoteyAppComposeTheme {
                // display all records  from room db
                val notes by noteViewModel.allNotes.observeAsState(emptyList())
                DisplayNotesList(notes)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteyAppComposeTheme {
        Greeting("Android")
    }
}