package learning.android.kotlin.feature.editNote

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import learning.android.kotlin.R

class CancelEditionNoteDialogFragment(): DialogFragment() {
    companion object {
        val DIALOG_ID = CancelEditionNoteDialogFragment::class.qualifiedName
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).let {
//            it.setView(R.layout.edit_note_cancel_dialog_fragment)
            it.setTitle("You have changes don't saved")
            it.setMessage("If you want to ignore your current modifications, click on \"Quit\" button. If not click on \"Continue Edition\" button.")
            it.setPositiveButton("Quit") { dialog, id ->
                dialog.dismiss()
                activity?.finish()
            }
            it.setNegativeButton("Continue Edition") { _,_ -> }
        }.create()
    }
}