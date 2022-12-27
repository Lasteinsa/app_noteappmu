package com.lasteinsa.noteappmu.core.domain.usecase

import com.lasteinsa.noteappmu.core.domain.repository.INotePreferencesRepository
import javax.inject.Inject

class NotePreferencesInteractor @Inject constructor(private val notePreferencesRepository: INotePreferencesRepository): NotePreferencesUseCase {

    override fun setTheme(theme: Boolean) {
        notePreferencesRepository.setTheme(theme)
    }

    override fun getTheme(): Boolean = notePreferencesRepository.getTheme()

    override fun getFirstLaunch(): Boolean = notePreferencesRepository.getFirstLaunch()

    override fun setFirstLaunched() {
        notePreferencesRepository.setFirstLaunched()
    }
}