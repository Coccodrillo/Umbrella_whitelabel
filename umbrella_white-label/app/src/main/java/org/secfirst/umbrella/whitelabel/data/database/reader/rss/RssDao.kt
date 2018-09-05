package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.ioContext

interface RssDao {

    suspend fun save(rss: RefRSSItem): Boolean {
        var result = 0L
        withContext(ioContext) {
            result = modelAdapter<RefRSSItem>().insert(rss)
        }
        return result != 0L
    }

    suspend fun saveAll(listRss: List<RefRSSItem>) {
        withContext(ioContext) {
            modelAdapter<RefRSSItem>().saveAll(listRss)
        }
    }

    suspend fun delete(rss: RefRSSItem) {
        withContext(ioContext) {
            modelAdapter<RefRSSItem>().delete(rss)
        }
    }

    suspend fun getAll(): List<RefRSSItem> {
        return withContext(ioContext) {
            SQLite.select()
                    .from(RefRSSItem::class.java)
                    .queryList()
        }
    }
}