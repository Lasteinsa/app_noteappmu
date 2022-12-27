package com.lasteinsa.noteappmu

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class MyApplication: Application() {
    @Inject
    lateinit var noteDatabase: com.lasteinsa.noteappmu.core.data.source.local.room.NoteDatabase
}