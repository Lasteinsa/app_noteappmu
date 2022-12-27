package com.lasteinsa.noteappmu.di

import com.lasteinsa.noteappmu.core.domain.usecase.NoteInteractor
import com.lasteinsa.noteappmu.core.domain.usecase.NotePreferencesInteractor
import com.lasteinsa.noteappmu.core.domain.usecase.NotePreferencesUseCase
import com.lasteinsa.noteappmu.core.domain.usecase.NoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNoteUseCase(noteInteractor: NoteInteractor): NoteUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideNotePreferencesUseCase(notePreferencesInteractor: NotePreferencesInteractor): NotePreferencesUseCase
}