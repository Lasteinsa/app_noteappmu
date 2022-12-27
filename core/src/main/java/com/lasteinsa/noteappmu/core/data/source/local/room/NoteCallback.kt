package com.lasteinsa.noteappmu.core.data.source.local.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lasteinsa.noteappmu.core.data.source.local.entity.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class NoteCallback(private val provider: Provider<NoteDao>): RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            prepopulateDatabase()
        }
    }

    private suspend fun prepopulateDatabase() {
        val note = NoteEntity(
            title = "Hello World",
            body = """
                This Note app is created by Lasteinsa
                Visit my website https://www.lasteinsa.com
            """.trimIndent(),
            createdAt = "11 : 25 PM, 27 Nov 2017"
        )
        provider.get().insertNote(note)
    }
}