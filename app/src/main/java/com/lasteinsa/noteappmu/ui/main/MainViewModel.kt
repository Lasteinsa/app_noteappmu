package com.lasteinsa.noteappmu.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.domain.usecase.NotePreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.lasteinsa.noteappmu.core.domain.usecase.NoteUseCase

@HiltViewModel
class MainViewModel @Inject constructor(private val noteUseCase: NoteUseCase, private val notePreferencesUseCase: NotePreferencesUseCase): ViewModel() {

    val note    = noteUseCase.getAllNote().asLiveData()

    fun searchNote(query: String): LiveData<List<Note>> {
        return noteUseCase.searchNote(query).asLiveData()
    }

    fun deleteNote(note: Note) = noteUseCase.deleteNote(note)

    fun setFirstLaunched() {
        notePreferencesUseCase.setFirstLaunched()
    }

    fun getFirstLaunch() = notePreferencesUseCase.getFirstLaunch()

    fun getTheme() = notePreferencesUseCase.getTheme()

    fun setTheme(theme: Boolean) {
        notePreferencesUseCase.setTheme(theme)
    }
}