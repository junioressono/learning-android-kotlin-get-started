package learning.android.kotlin.feature.consultNotesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import learning.android.kotlin.R
import learning.android.kotlin.feature.consultNoteDetails.Note

class NotesListMainRecyclerViewAdapter(
    private val notes: List<Note>?,
    private val onClickNoteListener: OnClickNoteListener
) : RecyclerView.Adapter<NotesListMainRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val excerptTextView = itemView.findViewById<TextView>(R.id.excerptTextView)
        val deleteImageButton = itemView.findViewById<ImageButton>(R.id.deleteImageButton)
        val moreImageButton = itemView.findViewById<ImageButton>(R.id.moreImageButton)
    }

    interface OnClickNoteListener {
        fun consultNoteDetailsAction(note: Note)
        fun deleteNoteAction(note: Note)
        fun seeMoreAction()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes?.get(position)
        if (note != null) {
            note.id = position
            holder.cardView.tag = position
            holder.let {
                it.cardView.let {
                    it.tag = position
                    it.setOnClickListener {
                        onClickNoteListener.consultNoteDetailsAction(note)
                    }
                    it.setOnLongClickListener {
                        onClickNoteListener.seeMoreAction()
                        true
                    }
                }
                it.titleTextView.text = note.title
                it.excerptTextView.text = note.content
                it.deleteImageButton.setOnClickListener(object: View.OnClickListener {
                    override fun onClick(view: View?) {
                        onClickNoteListener.deleteNoteAction(note)
                    }
                })
                it.moreImageButton.let {
                    it.setOnClickListener {
                        onClickNoteListener.seeMoreAction()
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return notes?.size ?: 0
    }
}