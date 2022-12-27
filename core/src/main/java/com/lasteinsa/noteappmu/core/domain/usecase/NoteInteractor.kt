package com.lasteinsa.noteappmu.core.domain.usecase

import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractor @Inject constructor(private val noteRepository: INoteRepository): NoteUseCase {

    override fun getAllNote(): Flow<List<Note>> = noteRepository.getAllNote()

    override fun searchNote(query: String): Flow<List<Note>> {
        return noteRepository.searchNote(query)
    }

    override fun getNoteById(id: Int): Note {
        return noteRepository.getNoteById(id)
    }

    override fun insertNote(note: Note) {
        return noteRepository.insertNote(note)
    }

    override fun deleteNote(note: Note) {
        return noteRepository.deleteNote(note)
    }

    override fun updateNote(note: Note) {
        return noteRepository.updateNote(note)
    }
}