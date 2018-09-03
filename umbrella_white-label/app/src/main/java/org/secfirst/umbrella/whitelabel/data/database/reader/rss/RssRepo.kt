package org.secfirst.umbrella.whitelabel.data.database.reader.rss

interface RssRepo {

    suspend fun saveFeed(rss: RefRSSItem)

    suspend fun delete(rss: RefRSSItem): Boolean

    suspend fun getAllFeeds(): List<RefRSSItem>
}