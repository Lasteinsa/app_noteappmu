package com.lasteinsa.noteappmu.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.lasteinsa.noteappmu.core.R
import com.lasteinsa.noteappmu.core.databinding.ItemNoteBinding
import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.utils.CardHelper

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ListViewHolder>() {

    val listData    = ArrayList<Note>()
    val selectedPositions   = mutableSetOf<Int>()
    var onItemClick: ((Note) -> Unit)? = null
    var onItemLongClick: ((Int, Note, MaterialCardView) -> Unit)? = null

    fun setData(newListData: List<Note>) {
        val diffCallback    = NoteDiffCallback(this.listData, newListData)
        val diffResult      = DiffUtil.calculateDiff(diffCallback)
        this.listData.clear()
        this.listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(data: Note) {
            with(binding) {
                noteTitle.text  = data.title
                noteBody.text   = data.body
            }
            if (adapterPosition in selectedPositions) {
                CardHelper().setCardOnSelect(itemView.context,binding.cardNote)
            } else {
                CardHelper().setCardUnSelect(itemView.context,binding.cardNote)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
            binding.root.setOnLongClickListener {
                onItemLongClick?.invoke(adapterPosition, listData[adapterPosition], binding.cardNote)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return  listData.size
    }
}