package com.lasteinsa.noteappmu.core.domain.usecase

interface NotePreferencesUseCase {

    fun getTheme(): Boolean

    fun setTheme(theme: Boolean)

    fun getFirstLaunch(): Boolean

    fun setFirstLaunched()
}