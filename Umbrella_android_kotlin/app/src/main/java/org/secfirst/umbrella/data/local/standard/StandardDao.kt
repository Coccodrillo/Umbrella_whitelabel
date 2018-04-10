package org.secfirst.umbrella.data.local.standard

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface StandardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(question: List<Standard>)

    @Query("SELECT * FROM standard_table")
    fun loadAll(): List<Standard>
}