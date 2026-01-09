package com.surajvanshsv.noteyappcompose.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class] , version = 1)
abstract class NotesDb : RoomDatabase(){

    abstract val notesdao : NoteDao

    companion object{
        private var INSTANCE : NotesDb?= null

        fun getInstance(context: Context) : NotesDb? {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    var instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDb::class.java,
                        "notes_db"
                    ).build()
                }
                INSTANCE = instance

                return instance
            }
        }
    }





}