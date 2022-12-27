package com.lasteinsa.noteappmu.core.domain.usecase

import com.lasteinsa.noteappmu.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteUseCase {

    fun getAllNote(): Flow<List<Note>>

    fun searchNote(query: String): Flow<List<Note>>

    fun getNoteById(id: Int): Note

    fun insertNote(note: Note)

    fun deleteNote(note: Note)

    fun updateNote(note: Note)
}