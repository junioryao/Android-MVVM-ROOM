package com.example.simpleappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleappmvvm.viewmodel.repository.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory(val repository: NoteRepository) : ViewModelProvider.Factory   {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){

            return NoteViewModel(repository) as T

        }
        throw IllegalArgumentException("unknown view model class")
    }


}