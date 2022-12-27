package com.lasteinsa.noteappmu.ui.setting

import androidx.lifecycle.ViewModel
import com.lasteinsa.noteappmu.core.domain.usecase.NotePreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val notePreferencesUseCase: NotePreferencesUseCase): ViewModel() {

    fun getTheme() = notePreferencesUseCase.getTheme()

    fun setTheme(theme: Boolean) {
        notePreferencesUseCase.setTheme(theme)
    }

}