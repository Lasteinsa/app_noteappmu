package com.lasteinsa.noteappmu.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note (
    val noteId: Int?,
    val title: String,
    val body: String,
    val createdAt: String
): Parcelable