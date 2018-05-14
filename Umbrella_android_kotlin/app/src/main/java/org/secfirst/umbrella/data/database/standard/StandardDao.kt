package org.secfirst.umbrella.data.database.standard

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import org.secfirst.umbrella.data.database.AppDatabase


interface StandardDao {


    fun insert(standard: Standard) = modelAdapter<Standard>().insert(standard)

    fun update(standard: Standard) = FlowManager.getDatabase(AppDatabase::class.java)
            .executeTransaction { databaseWrapper -> standard.update(databaseWrapper) }

    fun getStandard() = SQLite.select().from(Standard::class.java).queryList()

}
//FlowManager.getModelAdapter<Standard>(Standard::class.java).insert(standard)
//FlowManager.getDatabase(AppDatabase::class.java).executeTransaction { databaseWrapper -> standard.save(databaseWrapper) }

