package com.lasteinsa.noteappmu.core.data.source.local

import android.content.SharedPreferences
import com.lasteinsa.noteappmu.core.domain.repository.INotePreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotePreferencesRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
): INotePreferencesRepository {

    private val edit = sharedPreferences.edit()

    override fun setTheme(boolean: Boolean) {
        edit.putBoolean(DARK, boolean)
        edit.apply()
    }

    override fun getTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK, true)
    }

    override fun getFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    }

    override fun setFirstLaunched() {
        edit.putBoolean(FIRST_LAUNCH, false)
        edit.apply()
    }

    companion object {
        private const val DARK = "DARK"
        private const val FIRST_LAUNCH = "FIRST_LAUNCH"
    }
}