package com.lasteinsa.noteappmu.core.domain.repository

interface INotePreferencesRepository {

    fun getTheme(): Boolean

    fun setTheme(boolean: Boolean)

    fun getFirstLaunch(): Boolean

    fun setFirstLaunched()

}