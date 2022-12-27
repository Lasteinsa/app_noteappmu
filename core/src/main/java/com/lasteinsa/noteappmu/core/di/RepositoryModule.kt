package com.lasteinsa.noteappmu.core.di

import com.lasteinsa.noteappmu.core.data.source.local.NotePreferencesRepository
import com.lasteinsa.noteappmu.core.data.source.local.NoteRepository
import com.lasteinsa.noteappmu.core.domain.repository.INotePreferencesRepository
import com.lasteinsa.noteappmu.core.domain.repository.INoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(noteRepository: NoteRepository): INoteRepository

    @Binds
    abstract fun providePreferencesRepository(notePreferencesRepository: NotePreferencesRepository): INotePreferencesRepository
}