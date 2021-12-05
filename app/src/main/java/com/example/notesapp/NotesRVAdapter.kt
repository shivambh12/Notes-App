package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context:Context, private val listener:iNotesRVAdapter): RecyclerView.Adapter<NoteViewHolder>() {
    val allnotes=ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))
        viewHolder.deletebutton.setOnClickListener {
            listener.onitemClicked((allnotes[viewHolder.adapterPosition]))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val cuurentnote=allnotes[position]
        holder.textview.text=cuurentnote.text
    }

    override fun getItemCount(): Int {
        return allnotes.size
    }
    fun updateList(newList:List<Note>)
    {
        allnotes.clear()
        allnotes.addAll(newList)
        notifyDataSetChanged()
    }
}
class NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)
{
    val textview=itemview.findViewById<TextView>(R.id.textview)
    val deletebutton=itemview.findViewById<ImageView>(R.id.deletebutton)
}
interface iNotesRVAdapter
{
    fun onitemClicked(note:Note)
}
