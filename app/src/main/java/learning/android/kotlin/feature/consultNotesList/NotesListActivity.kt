package learning.android.kotlin.feature.consultNotesList

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import learning.android.kotlin.R
import learning.android.kotlin.databinding.NoteListActivityBinding
import learning.android.kotlin.feature.consultNoteDetails.Note
import learning.android.kotlin.feature.consultNoteDetails.NoteDetailsActivity
import learning.android.kotlin.feature.deleteNote.DeleteNoteConfirmDialogFragment
import learning.android.kotlin.feature.editNote.NoteEditingActivity
import learning.android.kotlin.shared.NotesApplication

class NotesListActivity : AppCompatActivity(),
    NotesListMainRecyclerViewAdapter.OnClickNoteListener {

    private lateinit var binding: NoteListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoteListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.notesListActivityTitle)

        binding.noteListRecyclerView.adapter =
            NotesListMainRecyclerViewAdapter(NotesApplication.NOTES.getAll(null), this)
        binding.noteListRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.addNoteFloatingActionButton.setOnClickListener { addNoteAction() }
    }

    override fun onResume() {
        super.onResume()
        binding.noteListRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun addNoteAction() {
        val intentToAddNote = Intent(this, NoteEditingActivity::class.java)
        startActivity(intentToAddNote)
    }

    override fun consultNoteDetailsAction(note: Note) {
//        Log.i(TAG, "Note ${note.title} details")
        val intent = Intent(this, NoteDetailsActivity::class.java)
        intent.putExtra(NoteDetailsActivity.KEY_OF_EXTRA_NOTE, note)
        startActivity(intent)
    }

    override fun deleteNoteAction(note: Note) {
        val dialog = DeleteNoteConfirmDialogFragment(note)
        dialog.showsDialog = true
        dialog.show(supportFragmentManager, DeleteNoteConfirmDialogFragment.DIALOG_ID)
        val msg = Message()
        msg.what = CODE_OF_NOTE_DELETED
        msg.data.putParcelable(KEY_OF_NOTE_TO_DELETE, note)

        handler.sendMessageDelayed(msg, 3000)
        NotesApplication.NOTES.remove(note.id)
        binding.noteListRecyclerView.adapter?.notifyDataSetChanged()

        val snackbar = Snackbar.make(binding.root,
            "Note \"${note.title}\" deleted",
            Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.cancelDeleteSnackbarActionButton) {
            handler.removeMessages(CODE_OF_NOTE_DELETED)
            NotesApplication.NOTES.add(note)
            binding.noteListRecyclerView.adapter?.notifyDataSetChanged()
        }
        snackbar.show()
    }

    override fun seeMoreAction() {
        Log.i(TAG, "More actions on note")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_list_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAllNotesMenuItem -> Log.i(TAG, "All notes deleted")
        }
        return super.onOptionsItemSelected(item)
    }

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                CODE_OF_NOTE_DELETED -> {
                    Log.i(TAG, "Remove confirmed")
                    /*NotesApplication.NOTES?.remove(msg.data.getParcelable<Note>(KEY_OF_NOTE_TO_DELETE)!!.id)
                    binding.noteListRecyclerView.adapter?.notifyDataSetChanged()*/
                }
            }
        }
    }

    companion object {
        private const val CODE_OF_NOTE_DELETED = 32202154
        private const val KEY_OF_NOTE_TO_DELETE =
            "learning.android.kotlin.feature.consultNotesList.NOTE_TO_DELETE"
        private val TAG = NotesListActivity::class.simpleName
    }
}