package org.secfirst.umbrella.data.local.standard

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.kotlinextensions.where
import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import org.secfirst.umbrella.data.local.AppDatabase


interface StandardDao {

    fun insert(standard: Standard) =
            FlowManager.getDatabase(AppDatabase::class.java).executeTransaction { databaseWrapper ->
                standard.save(databaseWrapper)
            }


    fun delete(standard: Standard) = standard.delete()

    fun update(standard: Standard) = standard.update()

    fun loadAll() = RXSQLite.rx(select from Standard::class where (Standard_Table.id.`is`(1))).querySingle()
}

