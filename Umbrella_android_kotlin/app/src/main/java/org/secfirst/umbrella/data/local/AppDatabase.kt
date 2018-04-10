package org.secfirst.umbrella.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.local.standard.StandardDao

@Database(entities = [(Standard::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun standardDao(): StandardDao
}