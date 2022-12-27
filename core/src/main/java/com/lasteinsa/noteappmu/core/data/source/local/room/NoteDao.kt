package com.lasteinsa.noteappmu.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lasteinsa.noteappmu.core.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("select * from note")
    fun getAllNote(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE title LIKE :query")
    fun searchNote(query: String): Flow<List<NoteEntity>>

    @Query("select * from note where noteId = :id")
    fun getNoteById(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)
}