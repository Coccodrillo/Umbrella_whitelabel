package org.secfirst.umbrella.data.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(version = AppDatabase.VERSION, generatedClassSeparator = "_", foreignKeyConstraintsEnforced = false)
object AppDatabase {

    const val NAME = "AppDatabase"
    const val VERSION = 1
}




