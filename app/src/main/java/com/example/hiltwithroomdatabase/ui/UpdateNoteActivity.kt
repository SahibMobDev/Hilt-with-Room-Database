package com.example.hiltwithroomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hiltwithroomdatabase.adapter.NoteAdapter
import com.example.hiltwithroomdatabase.databinding.ActivityUpdateNoteBinding
import com.example.hiltwithroomdatabase.db.NoteEntity
import com.example.hiltwithroomdatabase.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteEntity: NoteEntity

    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{
            noteId = it.getInt("NOTE_ID")
        }

        binding.apply {
            defaultTitle = repository.getNote(noteId).noteTitle
            defaultDesc = repository.getNote(noteId).noteDesc

            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteId, defaultTitle, defaultDesc)
                repository.deleteNote(noteEntity)
                finish()
            }

            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()

                if (title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(noteId, title, desc)
                    repository.updateNote(noteEntity)
                    finish()
                } else {
                    Toast.makeText(this@UpdateNoteActivity,
                        "Title or Description must be fill",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}