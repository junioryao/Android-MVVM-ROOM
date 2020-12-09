package com.example.simpleappmvvm.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDAO {

    // define all data base operation
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Update
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)

    // we want to delete everything we do
    @Query("DELETE FROM NOTE_TABLE " )
    suspend fun deleteall()

    // this must be an object of type LIST<NOTE>
    //add life data to obverse all changes of our return list
    @Query("SELECT * FROM Note_Table ORDER BY PRIORITY DESC")
    fun getallvalue() :LiveData < List<Note> >



}