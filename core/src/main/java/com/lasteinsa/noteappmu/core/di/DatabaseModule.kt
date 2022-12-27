package com.lasteinsa.noteappmu.core.di

import android.content.Context
import androidx.room.Room
import com.lasteinsa.noteappmu.core.data.source.local.room.NoteCallback
import com.lasteinsa.noteappmu.core.data.source.local.room.NoteDao
import com.lasteinsa.noteappmu.core.data.source.local.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, provider: Provider<NoteDao>): NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,"Note.db"
    ).addCallback(NoteCallback(provider)).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNoteDatabase(database: NoteDatabase): NoteDao = database.noteDao()
}