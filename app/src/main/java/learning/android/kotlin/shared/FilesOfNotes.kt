package learning.android.kotlin.shared

import learning.android.kotlin.feature.consultNoteDetails.Note

class FilesOfNotes(val isPrivate: Boolean): NotesDataBase {
    override fun add(note: Note): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(note: Collection<Note>) {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Note? {
        TODO("Not yet implemented")
    }

    override fun getAll(title: String?): List<Note>? {
        TODO("Not yet implemented")
    }

    override fun edit(note: Note) {
        TODO("Not yet implemented")
    }

    override fun edit(note: Collection<Note>) {
        TODO("Not yet implemented")
    }

    override fun remove(id: Int): Note {
        TODO("Not yet implemented")
    }

    override fun remove(ids: Set<Int>): Set<Note> {
        TODO("Not yet implemented")
    }

    override fun remove(note: Note): Note {
        TODO("Not yet implemented")
    }

    override fun remove(notes: Collection<Note>): Collection<Note> {
        TODO("Not yet implemented")
    }
}