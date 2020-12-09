package com.example.simpleappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleappmvvm.data.Note
import com.example.simpleappmvvm.viewmodel.NoteViewModel
import com.example.simpleappmvvm.viewmodel.NoteViewModelFactory
import com.example.simpleappmvvm.data.viewDatabase
import com.example.simpleappmvvm.databinding.ActivityAddNoteBinding
import com.example.simpleappmvvm.viewmodel.Recycleviewadaptor
import com.example.simpleappmvvm.viewmodel.repository.NoteRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.Serializable

//observer from data binding
class Add_note : AppCompatActivity() {


    private lateinit var binding: ActivityAddNoteBinding
    private  lateinit var adapter : Recycleviewadaptor

    private lateinit var noteviewmodel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        binding = setContentView(this,R.layout.activity_add_note)
        // add DAO instance
        val dao = viewDatabase.getInstance(application).noteDao
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)

        noteviewmodel = ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.addnotmodelview = noteviewmodel
        binding.lifecycleOwner=this
        recycleview()
    }
    fun recycleview(){
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        //noteviewmodel.allvalues.observe(this, {})
        displayallNote()

    }
    fun displayallNote(){

            // display DB element
            noteviewmodel.allvalues.observe(this , Observer { Log.i("try",it.toString())

            binding.recycleview.adapter = Recycleviewadaptor(it , {selected_note :Note->selectnote_modify(selected_note)}) })
            //lambda function
    }


    // handle a click on the recycle view

    private fun selectnote_modify(note: Note){

        //Toast.makeText(this,"trying ${note.title}" ,Toast.LENGTH_LONG ).show()
        noteviewmodel.objectsaver(note)
        var i = Intent(this, MainActivity::class.java)

        startActivity(i)
    }


}


