package learning.android.kotlin.feature.deleteNote

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import learning.android.kotlin.R
import learning.android.kotlin.databinding.DeleteNoteConfirmDialogFragmentBinding
import learning.android.kotlin.feature.consultNoteDetails.Note

class DeleteNoteConfirmDialogFragment(private val note: Note) : DialogFragment() {
    interface OnClickListener {
        fun onClick(view: View)
    }

    private var _binding: DeleteNoteConfirmDialogFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).let {
//            it.setView(R.layout.delete_note_confirm_dialog_fragment)
            it.setTitle("Confirm Deletion")
            it.setItems(R.id.positiveActionCardView, object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }

            })
//            it.setIcon(R.drawable.ic_baseline_delete_forever_24)
//            it.setItems(R.id.positiveActionCardView) { dialog, id ->
//
//            }
        }.create()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DeleteNoteConfirmDialogFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.titleTextView.text = note.title
        binding.excerptTextView.text = note.content
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val DIALOG_ID = DeleteNoteConfirmDialogFragment::class.qualifiedName
    }
}