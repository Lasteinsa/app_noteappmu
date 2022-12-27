package com.lasteinsa.noteappmu.core.utils

import java.text.SimpleDateFormat
import java.util.*

class DateHelper() {

    fun getCurrentDate(): String {
        val calendar    = Calendar.getInstance()
        val formatter   = SimpleDateFormat("h : mm a, dd MMM yyyy", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}