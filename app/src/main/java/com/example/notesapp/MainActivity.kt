package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), iNotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView=findViewById(R.id.reclyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allnotes.observe(this, Observer { list->
            list?.let{
                adapter.updateList(it)
            }
        })

    }

    override fun onitemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitnote(view: View) {
        val edittext:EditText=findViewById(R.id.input)
        val notetext=edittext.text.toString()
        if(notetext.isNotEmpty()) {
            viewModel.insertNote(Note(notetext))
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
        }
    }
}