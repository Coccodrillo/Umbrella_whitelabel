package org.secfirst.umbrella.whitelabel.data.database.rss

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.misc.AppExecutors

interface RssDao {

    suspend fun save(RSS: RSS) {
        withContext(AppExecutors.ioContext) {
            modelAdapter<RSS>().insert(RSS)
        }
    }

    suspend fun getAll(): List<RSS> = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(RSS::class.java)
                .queryList()
    }
}