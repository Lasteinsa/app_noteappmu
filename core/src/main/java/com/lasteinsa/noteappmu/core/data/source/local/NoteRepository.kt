package com.lasteinsa.noteappmu.core.data.source.local

import com.lasteinsa.noteappmu.core.data.source.local.room.NoteDatabase
import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.domain.repository.INoteRepository
import com.lasteinsa.noteappmu.core.utils.AppExecutors
import com.lasteinsa.noteappmu.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDatabase: NoteDatabase,
    private val appExecutors: AppExecutors
): INoteRepository {
    override  fun getAllNote(): Flow<List<Note>> {
        return noteDatabase.noteDao().getAllNote().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchNote(query: String): Flow<List<Note>> {
        return noteDatabase.noteDao().searchNote(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getNoteById(id: Int): Note {
        return DataMapper.EntitiesToDomain(
            noteDatabase.noteDao().getNoteById(id)
        )
    }

    override fun insertNote(note: Note) {
        appExecutors.diskIO().execute {
            noteDatabase.noteDao().insertNote(
                DataMapper.DomainToEntity(note)
            )
        }
    }

    override fun deleteNote(note: Note) {
        appExecutors.diskIO().execute {
            noteDatabase.noteDao().deleteNote(
                DataMapper.DomainToEntity(note)
            )
        }
    }

    override fun updateNote(note: Note) {
        appExecutors.diskIO().execute {
            noteDatabase.noteDao().updateNote(
                DataMapper.DomainToEntity(note)
            )
        }
    }

}