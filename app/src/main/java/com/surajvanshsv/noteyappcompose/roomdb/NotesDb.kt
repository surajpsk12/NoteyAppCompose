package com.surajvanshsv.noteyappcompose.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDb : RoomDatabase() {

    abstract val notesdao: NoteDao

    companion object {
        @Volatile // Thread safety ke liye
        private var INSTANCE: NotesDb? = null

        fun getInstance(context: Context): NotesDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDb::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
