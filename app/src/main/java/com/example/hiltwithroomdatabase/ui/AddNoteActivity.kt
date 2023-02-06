package com.example.hiltwithroomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hiltwithroomdatabase.R
import com.example.hiltwithroomdatabase.databinding.ActivityAddNoteBinding
import com.example.hiltwithroomdatabase.db.NoteEntity
import com.example.hiltwithroomdatabase.repository.DbRepository
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()

                if (title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(0, title, desc)
                    repository.saveNote(noteEntity)
                    finish()
                } else {
                    Toast.makeText(this@AddNoteActivity,
                        "Title or Description must be fill",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}