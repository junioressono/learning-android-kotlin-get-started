package learning.android.kotlin.feature.consultNoteDetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import learning.android.kotlin.R
import learning.android.kotlin.databinding.NoteDetailActivityBinding
import learning.android.kotlin.feature.deleteNote.DeleteNoteDialogFragment
import learning.android.kotlin.feature.editNote.NoteEditingActivity
import learning.android.kotlin.shared.NotesApplication

class NoteDetailsActivity : AppCompatActivity(),
    DeleteNoteDialogFragment.OnClickToDeleteNoteListener {
    private val TAG = NoteDetailsActivity::class.simpleName
    private val note: Note? by lazy { intent.getParcelableExtra<Note>(KEY_OF_EXTRA_NOTE) }

    companion object {
        val KEY_OF_EXTRA_NOTE = "learning.android.kotlin.feature.consultNoteDetails.NOTE"
        val KEY_OF_REQUEST_CODE_FOR_NOTE_EDITION = 1
    }

    private lateinit var binding: NoteDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoteDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.NoteDetailsActivityTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 4.0F

        binding.titleNoteDetailTextView.text = note?.title
        binding.contentNoteDetailTextView.text = note?.content

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_details_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.editNoteMenuItem -> {
                val intentToEdit = Intent(this, NoteEditingActivity::class.java)
                intentToEdit.putExtra(NoteEditingActivity.KEY_OF_EXTRA_NOTE, note)
                startForResult.launch(intentToEdit)
            }
            R.id.deleteAllNotesMenuItem -> {
                val dialogFragment = DeleteNoteDialogFragment(note!!, this)
                dialogFragment.show(supportFragmentManager, DeleteNoteDialogFragment.DIALOG_ID)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode) {
            NoteEditingActivity.KEY_OF_RESULT_CODE_NOTE_EDITED -> {
                val data = it.data?.getParcelableExtra<Note>(NoteEditingActivity.KEY_OF_EXTRA_NOTE)
                if (!note!!.equals(data) && data != null) {
                    note!!.id = data.id
                    note?.title = data.title
                    note?.content = data.content
                    binding.titleNoteDetailTextView.text = note?.title
                    binding.contentNoteDetailTextView.text = note?.content
                }
            }
        }
    }

    override fun deleteNoteAction(note: Note) {
        NotesApplication.NOTES.remove(note)
        finish()
    }
}