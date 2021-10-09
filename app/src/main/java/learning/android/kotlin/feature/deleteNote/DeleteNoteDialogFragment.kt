package learning.android.kotlin.feature.deleteNote

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import learning.android.kotlin.feature.consultNoteDetails.Note

class DeleteNoteDialogFragment(val note: Note, val clickListener: DeleteNoteDialogFragment.OnClickToDeleteNoteListener): DialogFragment() {
    companion object {
        val DIALOG_ID = DeleteNoteDialogFragment::class.qualifiedName
    }
    interface OnClickToDeleteNoteListener {
        fun deleteNoteAction(note: Note)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).let {
            it.setMessage("Are you sure to delete this note ?")
            it.setPositiveButton("Yes") { dialog, id ->
                clickListener.deleteNoteAction(note)
            }
            it.setNegativeButton("No") { _,_ ->
                // nothing to do
            }
        }.create()
    }
}