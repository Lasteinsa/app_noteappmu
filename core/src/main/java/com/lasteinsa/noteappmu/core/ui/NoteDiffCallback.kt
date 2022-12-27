package com.lasteinsa.noteappmu.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.lasteinsa.noteappmu.core.domain.model.Note

class NoteDiffCallback(private val mOldNote: List<Note>, private val mNewNote: List<Note>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNote.size
    }

    override fun getNewListSize(): Int {
        return mNewNote.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNote[oldItemPosition].noteId == mNewNote[newItemPosition].noteId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = mOldNote[oldItemPosition]
        val newNote = mNewNote[newItemPosition]
        return oldNote.noteId == newNote.noteId
    }
}