package org.secfirst.umbrella.whitelabel.data.database.reader.rss

interface RssRepo {

    suspend fun saveRss(rss: RefRSSItem)

    suspend fun saveAllRss(rssList: List<RefRSSItem>)

    suspend fun delete(rss: RefRSSItem)

    suspend fun getAllRss(): List<RefRSSItem>
}