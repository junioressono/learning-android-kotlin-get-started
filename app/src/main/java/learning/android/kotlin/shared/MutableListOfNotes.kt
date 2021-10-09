package learning.android.kotlin.shared

import learning.android.kotlin.feature.consultNoteDetails.Note

class MutableListOfNotes: NotesDataBase {
    private var notes: MutableList<Note> = mutableListOf<Note>()

    init {
        notes.add(Note(
            -1,
            "Lorem Ipsum",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean at faucibus augue. Praesent lectus ante, dictum vel euismod sit amet, tristique id leo. Fusce scelerisque condimentum metus bibendum convallis. Phasellus ac ornare eros. Sed urna purus, consequat ut hendrerit eget, condimentum vitae augue. Nullam luctus orci justo, finibus congue justo hendrerit in. Duis nec lobortis augue. Pellentesque placerat venenatis tincidunt. Aenean aliquam ipsum laoreet, ornare magna vel, blandit lacus. Mauris ultricies ante vel pulvinar auctor. Nullam a eros quam. Donec suscipit neque felis. In tincidunt erat non nisi scelerisque, quis rhoncus ligula malesuada. Curabitur consequat, ante in cursus placerat, diam lacus tempus ipsum, quis scelerisque libero justo sed leo."
        ))
        notes.add(Note(
            -1,
            "Proin sed felis",
            "Proin sed felis lacinia, interdum ligula egestas, tincidunt arcu. Mauris porttitor tortor non enim fermentum, vel interdum metus fringilla. Nullam at consectetur nibh. In facilisis leo eu nunc sagittis, pretium maximus ipsum gravida. Etiam gravida ac quam malesuada blandit. Vivamus nec dictum quam, sit amet mattis nisl. Etiam ornare consectetur finibus. Vivamus vitae volutpat sem.\n"
        ))
        notes.add(Note(
            -1,
            "Curabitur ut condimentum",
            "Curabitur ut condimentum neque. Cras lacinia nisi in dui porttitor venenatis in dapibus augue. Ut eros velit, eleifend non dui ut, mollis viverra est. Pellentesque lacinia turpis sapien. In aliquet pretium quam, ut rhoncus odio tempor nec. Maecenas vitae condimentum libero. Curabitur tempor ipsum sit amet enim egestas, nec interdum urna elementum."
        ))
        notes.add(Note(
            -1,
            "Sed sit amet laoreet erat",
            "Sed sit amet laoreet erat. Nulla ut ipsum odio. Sed massa sapien, efficitur iaculis congue quis, euismod eu nisi. Donec vitae auctor dui. In tempus porttitor orci, ut consectetur mauris interdum sed. Nulla hendrerit, libero eu posuere imperdiet, nisl nisi commodo ante, ut tincidunt velit augue in ligula. Aliquam condimentum cursus dui vel sodales. Fusce quis ante tincidunt erat ullamcorper placerat et id urna. Aliquam vitae semper libero, eget sodales odio. Nunc sodales nulla nec tempor iaculis. Mauris vestibulum magna pellentesque, commodo arcu et, tristique justo. Sed eu pellentesque sem. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam gravida a nibh non elementum. Proin vel eros et nisl facilisis dapibus et vitae tellus.\n"
        ))
        notes.add(Note(
            -1,
            "aliquam eu egestas odio",
            "Ut vel nibh quis libero aliquet aliquam eu egestas odio. Donec ullamcorper semper egestas. Pellentesque pulvinar nulla sed leo condimentum, in sagittis erat ultricies. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec eros mi, posuere in condimentum sed, fermentum at purus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc a lectus dictum, egestas nulla eget, fermentum lorem. Praesent lectus nisl, sagittis ac justo sit amet, placerat efficitur mauris. Suspendisse sed quam vitae urna euismod ornare. Donec eleifend dolor ut consectetur vestibulum.\n"
        ))
    }


    companion object {
        fun getInstance(): MutableListOfNotes? {
            return NotesApplication.NOTES as MutableListOfNotes?
        }
    }

    override fun add(note: Note): Boolean {
        if (note.id < 0)
            return notes.add(note)
        else {
            notes.add(note.id, note)
            return notes.get(note.id).equals(note)
        }
    }

    override fun add(note: Collection<Note>){}

    override fun get(id: Int): Note? = notes[id]

    override fun getAll(title: String?): List<Note>? = notes

    override fun edit(note: Note) {
        TODO("Not yet implemented")
    }

    override fun edit(note: Collection<Note>) {
        TODO("Not yet implemented")
    }

    override fun remove(id: Int) = notes.removeAt(id)
    override fun remove(ids: Set<Int>): Set<Note> = notes.filter { !ids.contains(it.id) }.toSet()

    override fun remove(note: Note): Note  = remove(note.id)

    override fun remove(notes: Collection<Note>): Collection<Note> {
        TODO("Not yet implemented")
    }
}