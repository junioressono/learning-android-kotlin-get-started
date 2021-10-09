package learning.android.kotlin.feature.editNote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import learning.android.kotlin.R
import learning.android.kotlin.databinding.NoteEditingActivityBinding
import learning.android.kotlin.feature.consultNoteDetails.Note
import learning.android.kotlin.shared.NotesApplication

class NoteEditingActivity : AppCompatActivity() {
    private val TAG = NoteEditingActivity::class.simpleName
    companion object {
        val KEY_OF_EXTRA_NOTE = "learning.android.kotlin.feature.editNote.NOTE"
        val KEY_OF_RESULT_CODE_NOTE_EDITED = 1
    }

    private lateinit var binding: NoteEditingActivityBinding
    private val note: Note? by lazy { intent.getParcelableExtra<Note>(KEY_OF_EXTRA_NOTE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoteEditingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.includedSharedToolbar.root)

//        note?.toString()?.let { Log.i(TAG, it) }
        if (note != null) {
            supportActionBar?.title = getString(R.string.noteEditingActivityTitle)
            binding.titleNoteDetailTextView.text = Editable.Factory.getInstance().newEditable(note?.title)
            binding.contentNoteDetailTextView.text = Editable.Factory.getInstance().newEditable(note?.content)
        } else {
            supportActionBar?.title = getString(R.string.noteEditingNewActivityTitle)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_editing_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.cancelEditingNoteMenuItem -> {
                if (note != null) {
                    val newNote = Note(
                        note!!.id,
                        title = binding.titleNoteDetailTextView.text.toString(),
                        content = binding.contentNoteDetailTextView.text.toString()
                    )
                    if (!note!!.equals(newNote)) {
                        CancelEditionNoteDialogFragment()
                            .show(supportFragmentManager, CancelEditionNoteDialogFragment.DIALOG_ID)
                    }
                } else {
                    if (binding.titleNoteDetailTextView.text?.isNotEmpty() == true || binding.contentNoteDetailTextView.text?.isNotEmpty() == true)
                        CancelEditionNoteDialogFragment()
                            .show(supportFragmentManager, CancelEditionNoteDialogFragment.DIALOG_ID)
                }

            }
            R.id.saveEditingNoteMenuItem -> {

                if (note == null) {
                    saveEditingNewNoteAction()
                } else
                    saveEditingNoteAction()

                /*NotesApplication.notes.forEach {
                    Log.i(TAG, it.toString())
                }*/
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveEditingNoteAction() {
        Log.i(TAG, note.toString())
        NotesApplication.NOTES.get(note!!.id).let {
            it?.id = note!!.id
            it?.title = binding.titleNoteDetailTextView.text.toString()
            it?.content = binding.contentNoteDetailTextView.text.toString()
        }
        val intentToNoteDetailsActivity = Intent()
        intentToNoteDetailsActivity.putExtra(KEY_OF_EXTRA_NOTE, NotesApplication.NOTES.get(note!!.id))
        setResult(KEY_OF_RESULT_CODE_NOTE_EDITED, intentToNoteDetailsActivity)
        finish()
    }

    private fun saveEditingNewNoteAction() {
        val newNote = Note(title = binding.titleNoteDetailTextView.text.toString(), content = binding.contentNoteDetailTextView.text.toString())
        if (NotesApplication.NOTES.add(newNote)){
            finish()
        }
    }
}