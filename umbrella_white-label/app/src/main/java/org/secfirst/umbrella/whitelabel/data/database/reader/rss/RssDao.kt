package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.misc.AppExecutors

interface RssDao {

    suspend fun save(rss: RSS) {
        withContext(AppExecutors.ioContext) {
            modelAdapter<RSS>().insert(rss)
        }
    }

    suspend fun getAll(): List<RSS> = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(RSS::class.java)
                .queryList()
    }

    suspend fun delete(rss: RSS) = withContext(AppExecutors.ioContext) {
        modelAdapter<RSS>().delete(rss)
    }
}