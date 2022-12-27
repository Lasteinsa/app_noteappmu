package com.lasteinsa.noteappmu.ui.note

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.lasteinsa.noteappmu.core.domain.usecase.NoteUseCase
import com.lasteinsa.noteappmu.core.domain.model.Note

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: NoteUseCase): ViewModel() {
    fun save(note: Note) {
        noteUseCase.insertNote(note)
    }
    fun deleteNote(note: Note) {
        noteUseCase.deleteNote(note)
    }
}