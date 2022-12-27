package com.lasteinsa.noteappmu.core.utils

import com.lasteinsa.noteappmu.core.data.source.local.entity.NoteEntity
import com.lasteinsa.noteappmu.core.domain.model.Note

object DataMapper {
    fun mapEntitiesToDomain(input: List<NoteEntity>):List<Note> =
        input.map {
            Note(
                noteId  = it.noteId,
                title   = it.title,
                body    = it.body,
                createdAt = it.createdAt
            )
        }

    fun mapDomainToEntity(input: List<Note>) =
        input.map {
            NoteEntity(
                noteId  = it.noteId,
                title   = it.title,
                body    = it.body,
                createdAt = it.createdAt
            )
        }

    fun EntitiesToDomain(input: NoteEntity) = Note(input.noteId, input.title, input.body, input.createdAt)

    fun DomainToEntity(input: Note) = NoteEntity(input.noteId, input.title, input.body, input.createdAt)
}