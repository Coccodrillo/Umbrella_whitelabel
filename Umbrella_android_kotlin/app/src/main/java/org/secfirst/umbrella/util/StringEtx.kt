package org.secfirst.umbrella.util

import java.text.SimpleDateFormat
import java.util.*


val currenttime: String
    get() {
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH)
        return dateFormat.format(Date())
    }