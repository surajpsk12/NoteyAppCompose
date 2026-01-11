package com.surajvanshsv.noteyappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.surajvanshsv.noteyappcompose.repository.NotesRepository
import com.surajvanshsv.noteyappcompose.roomdb.Note
import com.surajvanshsv.noteyappcompose.roomdb.NoteDao
import com.surajvanshsv.noteyappcompose.roomdb.NotesDb
import com.surajvanshsv.noteyappcompose.screens.DisplayDialog
import com.surajvanshsv.noteyappcompose.screens.DisplayNotesList
import com.surajvanshsv.noteyappcompose.ui.theme.NoteyAppComposeTheme
import com.surajvanshsv.noteyappcompose.viewmodel.NoteViewModel
import com.surajvanshsv.noteyappcompose.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // room db
        val database = NotesDb.getInstance(context = applicationContext)
        val daoNote = database.notesdao
        // repository
        val repository = NotesRepository(daoNote )

        // viewmodel factory
        val viewModelFactory = NoteViewModelFactory(repository)

        // viwemodel
        val noteViewModel = ViewModelProvider(this,
            viewModelFactory)[NoteViewModel::class.java]





        setContent {
            NoteyAppComposeTheme {

                Scaffold(floatingActionButton = {MyFab(noteViewModel)},
                    containerColor = Color(0xFFF8F9FA)) {
                    val notes by noteViewModel.allNotes.observeAsState(emptyList())
                    DisplayNotesList(notes)
                }

            }
        }
    }
}


@Composable
fun MyFab(viewModel: NoteViewModel){
    var showDialog by rememberSaveable { mutableStateOf(false) }

    DisplayDialog(
        viewModel,
        showDialog = showDialog,
    ){
        showDialog = false
    }

    FloatingActionButton(
        containerColor = Color(0xFFCCC03D),
        onClick = {showDialog = true},
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add Note"
        )
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