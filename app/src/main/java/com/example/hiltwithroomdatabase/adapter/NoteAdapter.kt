package com.example.hiltwithroomdatabase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltwithroomdatabase.databinding.ItemNoteBinding
import com.example.hiltwithroomdatabase.db.NoteEntity
import com.example.hiltwithroomdatabase.ui.UpdateNoteActivity
import javax.inject.Inject

class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    lateinit var binding: ItemNoteBinding
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteEntity) {
            binding.apply {
                tvTitle.text = item.noteTitle
                tvDesc.text = item.noteDesc
                root.setOnClickListener {
                    val intent = Intent(context, UpdateNoteActivity::class.java)
                    intent.putExtra("NOTE_ID", item.noteId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}