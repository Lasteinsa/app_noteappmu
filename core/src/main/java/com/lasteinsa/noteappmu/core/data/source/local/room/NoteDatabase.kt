package com.lasteinsa.noteappmu.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lasteinsa.noteappmu.core.data.source.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}