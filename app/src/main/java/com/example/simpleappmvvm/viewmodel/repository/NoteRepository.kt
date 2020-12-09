package com.example.simpleappmvvm.viewmodel.repository


import androidx.lifecycle.LiveData
import com.example.simpleappmvvm.data.Note
import com.example.simpleappmvvm.data.NoteDAO

class NoteRepository( val noteDAO: NoteDAO) {
    // implement all the DAO , by creating an object of it
    //var notedao :NoteDAO?=null
    // handle every return value
    var all_notes : LiveData < List<Note>> = noteDAO.getallvalue()


    // configurer all DAO function

    suspend fun insert(note: Note){
        return noteDAO.insert(note)
    }

    suspend fun update(note: Note){
        return noteDAO.update(note)
    }

    suspend fun delete(note: Note){
        return noteDAO.delete(note)
    }

    suspend fun deleteall(){
        return noteDAO.deleteall()
    }



}