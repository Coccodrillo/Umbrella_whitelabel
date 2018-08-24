package org.secfirst.umbrella.whitelabel.data.database.rss

interface RssRepo {

    suspend fun saveFeed(rss: RSS)

    suspend fun delete(rss: RSS): Boolean

    suspend fun getAllFeeds(): List<RSS>
}