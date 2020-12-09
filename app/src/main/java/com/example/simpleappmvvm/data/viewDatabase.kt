package com.example.simpleappmvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = arrayOf(Note::class), version = 1)
abstract class viewDatabase: RoomDatabase() {

    abstract val noteDao : NoteDAO
    companion object{

        @Volatile
        private  var  instance_db : viewDatabase?=null

        // write a function that return a synchronized obj of our DB instance

        fun getInstance(context :Context): viewDatabase {
            synchronized(this){

                var instance = instance_db

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        viewDatabase::class.java,"note database")
                        .build()
                }
                return instance
            }
        }
    }

}
