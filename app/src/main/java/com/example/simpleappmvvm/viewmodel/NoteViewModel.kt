package com.example.simpleappmvvm.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.simpleappmvvm.Add_note
import com.example.simpleappmvvm.viewmodel.repository.NoteRepository
import com.example.simpleappmvvm.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//contain application reference

class NoteViewModel(private val repository: NoteRepository) : ViewModel() ,Observable  {

        //get live data

         val allvalues = repository.all_notes
        companion object{
                lateinit var  noteobject :Note

        }

        // define all the input and output variable as Mutable live data
        //only string value

        val title= MutableLiveData<String>()
        val description = MutableLiveData<String>()
        val priority = MutableLiveData<String>()

        var titleupdate = MutableLiveData<String>()
        var descriptionupdate = MutableLiveData<String>()
        var priorityupdate = MutableLiveData<String>()
        // define every function  here as well


        fun Savedata(){
                if (title.value.isNullOrEmpty() or description.value.isNullOrEmpty() or priority.value.isNullOrEmpty()){
                        return

                }
                val title = title.value!!
                val desc = description.value!!
                val prio = priority.value!!
                insert(Note(0,title,desc,prio))
                this.title.value =null
                this.description.value=null
                this.priority.value = null
        }



        fun UpdateNote(){

        }

        fun deleteallNote(){
                viewModelScope.launch {
                        repository.deleteall()
                }
        }

        //pass the data
        fun insert(note: Note){

                viewModelScope.launch {
                        repository.insert(note)
                }

        }

        fun update(note: Note){
                viewModelScope.launch {
                        repository.update(note)
                }
        }

        fun delete(note: Note){
                viewModelScope.launch {
                        repository.delete(note)
                }
        }


        fun objectsaver(note: Note){

             noteobject=note


        }
        fun getobjectsaved(): Note {
                return noteobject
        }


        fun modify(){
                Log.i("check" ,  "note.title")
                noteobject.title = titleupdate.value.toString()
                noteobject.description = descriptionupdate.value.toString()
                noteobject.priority = priorityupdate.value.toString()
                update(noteobject)
        }
        fun deleteobj(){

                delete(noteobject)
        }


        override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

        }

        override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

        }

}