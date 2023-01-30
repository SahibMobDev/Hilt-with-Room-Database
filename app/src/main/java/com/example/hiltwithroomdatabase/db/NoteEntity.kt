package com.example.hiltwithroomdatabase.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hiltwithroomdatabase.utils.Constants.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    @ColumnInfo("note_title")
    val noteTitle: String,
    @ColumnInfo("note_desc")
    val noteDesc: String
        )