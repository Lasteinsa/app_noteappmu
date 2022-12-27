package com.lasteinsa.noteappmu.core.di

import android.content.Context
import android.content.SharedPreferences
import com.lasteinsa.noteappmu.core.data.source.local.NotePreferencesRepository
import com.lasteinsa.noteappmu.core.domain.repository.INotePreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotePreferencesModule {

    @Provides
    @Singleton
    fun provideNotePreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("note_prefs", Context.MODE_PRIVATE)
    }
}