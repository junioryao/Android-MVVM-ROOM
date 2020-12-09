package com.example.simpleappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleappmvvm.data.Note
import com.example.simpleappmvvm.data.viewDatabase
import com.example.simpleappmvvm.databinding.ActivityAddNoteBinding
import com.example.simpleappmvvm.databinding.ActivityMainBinding
import com.example.simpleappmvvm.viewmodel.NoteViewModel
import com.example.simpleappmvvm.viewmodel.NoteViewModelFactory
import com.example.simpleappmvvm.viewmodel.Recycleviewadaptor
import com.example.simpleappmvvm.viewmodel.repository.NoteRepository
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

   // private lateinit var noteviewmodel: NoteViewModel
    // add recycle view adapter

    private lateinit var noteviewmodel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val obj  = NoteViewModel.noteobject

        Toast.makeText(this,"${obj.title}",Toast.LENGTH_LONG).show()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // add DAO instance
        val dao = viewDatabase.getInstance(application).noteDao
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)

        noteviewmodel = ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.modifyActivity = noteviewmodel
        binding.lifecycleOwner=this


        findViewById<EditText>(R.id.editTextTextPersonName).hint = obj.title
        findViewById<EditText>(R.id.editTextTextPersonName2).hint = obj.description
        findViewById<EditText>(R.id.editTextTextPersonName3).hint = obj.priority



    }




}