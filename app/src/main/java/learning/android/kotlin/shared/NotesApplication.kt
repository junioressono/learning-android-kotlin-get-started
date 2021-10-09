package learning.android.kotlin.shared

import android.app.Application

class NotesApplication: Application() {
    private val TAG = NotesApplication::class.simpleName

    companion object {
        val DATA_STORAGE_TYPE = DataStroreType.TEMPORARY_STORAGE_IN_MUTABLE_LIST
        lateinit var NOTES: NotesDataBase
    }

    override fun onCreate() {
        super.onCreate()
        NOTES = when (DATA_STORAGE_TYPE) {
            DataStroreType.PREMANENT_STORAGE_IN_SQL_LITE -> SQLiteOfNotes()
            DataStroreType.PREMANENT_STORAGE_IN_PUBLIC_FILES -> FilesOfNotes(false)
            DataStroreType.PREMANENT_STORAGE_IN_PRIVATE_FILES -> FilesOfNotes(true)
            DataStroreType.TEMPORARY_STORAGE_IN_MUTABLE_LIST -> MutableListOfNotes()
        }
    }
}