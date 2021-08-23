package com.lugotorix.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lugotorix.noteapp.R
import com.lugotorix.noteapp.model.Note
import com.lugotorix.noteapp.view.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.item_container.view.*

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]

        holder.itemView.title_txt.text = currentItem.title
        holder.itemView.topic_txt.text = currentItem.topic
        holder.itemView.date_txt.text = currentItem.date

        holder.itemView.item_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}