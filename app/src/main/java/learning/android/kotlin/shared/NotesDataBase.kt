package learning.android.kotlin.shared

import learning.android.kotlin.feature.consultNoteDetails.Note

interface NotesDataBase {
    //CREATES
    fun add(note: Note): Boolean
    fun add(note: Collection<Note>)

    //READS
    fun get(id: Int): Note?
    fun getAll(title: String?): List<Note>?

    //UPDATES
    fun edit(note: Note)
    fun edit(note: Collection<Note>)

    //DELETES
    fun remove(id: Int): Note
    fun remove(ids: Set<Int>): Set<Note>
    fun remove(note: Note): Note
    fun remove(notes: Collection<Note>): Collection<Note>
}
